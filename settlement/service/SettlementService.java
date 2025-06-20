package com.fimepay.merchantapp.settlement.service;

import com.fimepay.merchantapp.settlement.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface SettlementService {
    SettlementDTO scheduleSettlement(UUID merchantId, CreateSettlementRequest req);
    Page<SettlementDTO> listSettlements(UUID merchantId, Pageable pageable);
    SettlementDTO runSettlementNow(UUID merchantId, UUID settlementId);
}
 

 