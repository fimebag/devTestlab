package com.fimepay.merchantapp.transactions.service;

import com.fimepay.merchantapp.transactions.dto.TransactionAlertDTO;
import com.fimepay.merchantapp.transactions.dto.CreateAlertRequest;
import com.fimepay.merchantapp.transactions.model.Transaction;
import java.util.List;
import java.util.UUID;

public interface AlertService {
    void evaluateAlerts(Transaction tx);
    List<TransactionAlertDTO> listAlerts(UUID merchantId);
    TransactionAlertDTO createAlert(UUID merchantId, CreateAlertRequest req);
}
