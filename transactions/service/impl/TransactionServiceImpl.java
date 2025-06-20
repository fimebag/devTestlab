package com.fimepay.merchantapp.transactions.service.impl;

import com.fimepay.merchantapp.exception.ResourceNotFoundException;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.WalletType;
import com.fimepay.merchantapp.repository.MerchantRepository;
import com.fimepay.merchantapp.payout.PaymentGatewayService;
import com.fimepay.merchantapp.transactions.dto.CreatePayoutRequest;
import com.fimepay.merchantapp.transactions.dto.TransactionDTO;
import com.fimepay.merchantapp.transactions.model.PaymentChannel;
import com.fimepay.merchantapp.transactions.model.Transaction;
import com.fimepay.merchantapp.transactions.model.TransactionStatus;
import com.fimepay.merchantapp.transactions.model.TransactionType;
import com.fimepay.merchantapp.transactions.repository.TransactionRepository;
import com.fimepay.merchantapp.transactions.service.TransactionService;
import com.fimepay.merchantapp.service.WalletService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Executor;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository txRepo;
    private final MerchantRepository merchantRepo;
    private final WalletService walletService;
    private final PaymentGatewayService gatewayService;
    private final ModelMapper mapper;

    public TransactionServiceImpl(TransactionRepository txRepo,
                                  MerchantRepository merchantRepo,
                                  WalletService walletService,
                                  PaymentGatewayService gatewayService,
                                  ModelMapper mapper) {
        this.txRepo = txRepo;
        this.merchantRepo = merchantRepo;
        this.walletService = walletService;
        this.gatewayService = gatewayService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionDTO> listTransactions(UUID merchantId,
                                                 TransactionType type,
                                                 TransactionStatus status,
                                                 PaymentChannel channel,
                                                 Pageable pageable) {
        return txRepo.findByMerchant_Id(merchantId, pageable)
                     .map(tx -> mapper.map(tx, TransactionDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public TransactionDTO getDetails(UUID id) {
        Transaction tx = txRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + id));
        return mapper.map(tx, TransactionDTO.class);
    }

    @Override
    public TransactionDTO initiatePayout(UUID merchantId, CreatePayoutRequest req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new ResourceNotFoundException("Merchant not found: " + merchantId));
            

        // 1) Create and save the pending Transaction
        Transaction tx = new Transaction();
        tx.setMerchant(m);
        tx.setType(TransactionType.OUTBOUND);
        tx.setChannel(req.getChannel());
        tx.setAmount(req.getAmount());
        tx.setCurrency(req.getCurrency());
        tx.setChannelReference(req.getChannelReference());
        tx.setStatus(TransactionStatus.PENDING);
        tx.setCreatedAt(LocalDateTime.now());
        tx.setReference("TXN_" + UUID.randomUUID()
        .toString().replace("-", "")
        .substring(0, 16).toUpperCase());

        // 2) Persist to get the generated ID
        Transaction saved = txRepo.save(tx);

          // 3) Debit the merchant wallet immediately
          // Using the newly‐saved transaction’s UUID as your “referenceId”
        walletService.debit(
            merchantId.toString(),
            WalletType.PAY_OUT,          // or whatever enum you use for payouts
            req.getCurrency(),
            req.getAmount(),
            saved.getId()                // UUID of the Transaction you just saved
        );

        // 3) Fire-and-forget the external gateway call
        processPayoutAsync(
            saved.getId(),
            merchantId,
            saved.getReference(),
            saved.getAmount(),
            saved.getCurrency()
        );
        // 5) Return the DTO (still PENDING)
        return mapper.map(saved, TransactionDTO.class);
    }

    /**
     * Runs in the background, on the gatewayTaskExecutor pool.
     */
    @Async("gatewayTaskExecutor")
    public void processPayoutAsync(UUID transactionId,
                                   UUID merchantId,
                                   String reference,
                                   BigDecimal amount,
                                   String currency) {
        try {
            String externalId = gatewayService.sendPayout(reference, merchantId, amount, currency);
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setExternalId(externalId);
                tx.setStatus(TransactionStatus.COMPLETED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
        } catch (Exception ex) {
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setStatus(TransactionStatus.FAILED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
            // TODO: add logging or retry logic here
        }
    }

    @Override
    public void initiateOutboundRefund(UUID merchantId,
                                       UUID originalTransactionId,
                                       BigDecimal amount,
                                       UUID refundRequestId) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new ResourceNotFoundException("Merchant not found: " + merchantId));
        Transaction orig = txRepo.findById(originalTransactionId)
            .orElseThrow(() -> new ResourceNotFoundException("Original transaction not found: " + originalTransactionId));
    
            // 1) Build refund transaction (PENDING)
        Transaction refundTx = new Transaction();
        refundTx.setMerchant(m);
        refundTx.setType(TransactionType.REFUND);
        refundTx.setChannel(orig.getChannel());
        refundTx.setOriginalTransaction(orig);
        refundTx.setAmount(amount);
        refundTx.setCurrency(orig.getCurrency());
        refundTx.setChannelReference(refundRequestId.toString());
        refundTx.setStatus(TransactionStatus.PENDING);
        refundTx.setCreatedAt(LocalDateTime.now());
        refundTx.setReference("RF_" + UUID.randomUUID()
        .toString().replace("-", "")
        .substring(0, 16).toUpperCase());

    /*    String refundRef = "RF_" + UUID
            .randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 16)
            .toUpperCase();
        refundTx.setReference(refundRef); */
    
        // 2) Persist to get refundTx.getId()
        Transaction savedRefund = txRepo.save(refundTx);
                            
        // 3) Debit the wallet (use savedRefund.getId())
        walletService.debit(
            merchantId.toString(),
            WalletType.PAY_OUT,
            savedRefund.getCurrency(),
            savedRefund.getAmount(),
            savedRefund.getId()
        );

   
        // 4) Async call to gateway
        processRefundAsync(
            savedRefund.getId(),
            orig.getReference(),
            savedRefund.getReference(),
            savedRefund.getAmount()
        );

    }

    @Async("gatewayTaskExecutor")
    public void processRefundAsync(UUID transactionId,
                                   String originalReference,
                                   String refundReference,
                                   BigDecimal amount) {
        try {
            String externalId = gatewayService.sendRefund(originalReference, refundReference, amount);
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setExternalId(externalId);
                tx.setStatus(TransactionStatus.COMPLETED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
        } catch (Exception ex) {
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setStatus(TransactionStatus.FAILED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
            // TODO: logging / retry
        }
    }

    
    @Override
    public void initiateChargebackPayout(UUID merchantId,
                                         UUID originalTransactionId,
                                         BigDecimal amount,
                                         UUID chargebackEventId) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new ResourceNotFoundException("Merchant not found: " + merchantId));
        Transaction orig = txRepo.findById(originalTransactionId)
            .orElseThrow(() -> new ResourceNotFoundException("Original transaction not found: " + originalTransactionId));
        
        // 1) Build chargeback transaction (PENDING)
        Transaction cbTx = new Transaction();
        cbTx.setMerchant(m);
        cbTx.setType(TransactionType.CHARGEBACK);
        cbTx.setChannel(orig.getChannel());
        cbTx.setOriginalTransaction(orig);
        cbTx.setAmount(amount);
        cbTx.setCurrency(orig.getCurrency());
        cbTx.setChannelReference(chargebackEventId.toString());
        cbTx.setStatus(TransactionStatus.PENDING);
        cbTx.setCreatedAt(LocalDateTime.now());
        cbTx.setReference("CB_" + UUID.randomUUID()
        .toString().replace("-", "")
        .substring(0, 16).toUpperCase());

         // 2) Persist to get cbTx.getId()
        Transaction savedCb = txRepo.save(cbTx);

          // 3) Debit the wallet (use savedCb.getId())
        walletService.debit(
            merchantId.toString(),
            WalletType.PAY_OUT,
            savedCb.getCurrency(),
            savedCb.getAmount(),
            savedCb.getId()
        );
       
         // 4) Async gateway call
        processChargebackAsync(
            savedCb.getId(),
            orig.getReference(),
            savedCb.getReference(),
            savedCb.getAmount()
        );
    }

    @Async("gatewayTaskExecutor")
    public void processChargebackAsync(UUID transactionId,
                                       String originalReference,
                                       String cbReference,
                                       BigDecimal amount) {
        try {
            String externalId = gatewayService.sendChargeback(originalReference, cbReference, amount);
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setExternalId(externalId);
                tx.setStatus(TransactionStatus.COMPLETED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
        } catch (Exception ex) {
            txRepo.findById(transactionId).ifPresent(tx -> {
                tx.setStatus(TransactionStatus.FAILED);
                tx.setConfirmedAt(LocalDateTime.now());
                txRepo.save(tx);
            });
            // TODO: logging / retry
        }
    }
}
