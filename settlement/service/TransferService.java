package com.fimepay.merchantapp.settlement.service;

import com.fimepay.merchantapp.settlement.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface TransferService {
    InternalTransferDTO createTransfer(UUID merchantId, CreateTransferRequest req);
    Page<InternalTransferDTO> listTransfers(UUID merchantId, Pageable pageable);
    InternalTransferDTO approveTransfer(UUID merchantId, UUID transferId);
}

