package com.fimepay.merchantapp.chargebacks.service;

import com.fimepay.merchantapp.chargebacks.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ChargebackService {
    ChargebackEventDTO createChargeback(
        UUID merchantId,
        UUID transactionId,
        CreateChargebackRequestDTO req
    );
    ChargebackEventDTO disputeChargeback(UUID merchantId, UUID chargebackId);
    ChargebackEventDTO resolveChargeback(UUID merchantId, UUID chargebackId);
    Page<ChargebackEventDTO> listChargebacks(UUID merchantId, Pageable pageable);
    ChargebackEventDTO getChargeback(UUID merchantId, UUID chargebackId);
}