package com.fimepay.merchantapp.settlement.service;

import com.fimepay.merchantapp.settlement.dto.FxConversionDTO;
import com.fimepay.merchantapp.settlement.dto.CreateFxRequest;
import java.util.UUID;

public interface FxService {
    FxConversionDTO convert(UUID merchantId, CreateFxRequest req);
}

