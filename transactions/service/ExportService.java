package com.fimepay.merchantapp.transactions.service;

import com.fimepay.merchantapp.transactions.dto.*;
import java.util.UUID;

public interface ExportService {
    TransactionExportJobDTO scheduleExport(UUID merchantId, CreateExportJobRequest req);
    byte[] runExportNow(UUID jobId);
}
