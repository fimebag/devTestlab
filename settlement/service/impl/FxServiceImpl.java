package com.fimepay.merchantapp.settlement.service.impl;

import com.fimepay.merchantapp.settlement.dto.*;
import com.fimepay.merchantapp.settlement.model.*;
import com.fimepay.merchantapp.settlement.repository.FxConversionRepository;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.Wallet;
import com.fimepay.merchantapp.repository.MerchantRepository;
import com.fimepay.merchantapp.repository.WalletRepository;
import com.fimepay.merchantapp.settlement.service.FxService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class FxServiceImpl implements FxService {

    private final FxConversionRepository repo;
    private final MerchantRepository merchantRepo;
    private final WalletRepository walletRepo;
    private final ModelMapper mapper;

    public FxServiceImpl(FxConversionRepository repo,
                         MerchantRepository merchantRepo,
                         WalletRepository walletRepo,
                         ModelMapper mapper) {
        this.repo = repo;
        this.merchantRepo = merchantRepo;
        this.walletRepo = walletRepo;
        this.mapper = mapper;
    }

    @Override
    public FxConversionDTO convert(UUID merchantId, CreateFxRequest req) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));
        Wallet from = walletRepo.findById(UUID.fromString(req.getFromWalletId()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));
        Wallet to = walletRepo.findById(UUID.fromString(req.getToWalletId()))
            .orElseThrow(() -> new RuntimeException("Wallet not found"));

        BigDecimal out = req.getAmountIn().multiply(req.getRate());

        FxConversion fx = new FxConversion();
        fx.setMerchant(m);
        fx.setFromWallet(from);
        fx.setToWallet(to);
        fx.setAmountIn(req.getAmountIn());
        fx.setRate(req.getRate());
        fx.setAmountOut(out);
        fx.setStatus(ConversionStatus.COMPLETED);

        FxConversion saved = repo.save(fx);
        return mapper.map(saved, FxConversionDTO.class);
    }
}
