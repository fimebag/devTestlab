package com.fimepay.merchantapp.refunds.service;

import com.fimepay.merchantapp.refunds.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface RefundService {
    RefundRequestDTO requestRefund(
        UUID merchantId,
        UUID transactionId,
        CreateRefundRequestDTO req
    );
    RefundRequestDTO approveRefund(
        UUID merchantId,
        UUID refundId,
        ApproveRefundRequestDTO req
    );
    RefundRequestDTO rejectRefund(
        UUID merchantId,
        UUID refundId,
        RejectRefundRequestDTO req
    );
    Page<RefundRequestDTO> listRefunds(UUID merchantId, Pageable pageable);
    RefundRequestDTO getRefund(UUID merchantId, UUID refundId);
}