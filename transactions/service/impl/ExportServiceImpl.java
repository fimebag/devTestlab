package com.fimepay.merchantapp.transactions.service.impl;

import com.fimepay.merchantapp.transactions.dto.*;
import com.fimepay.merchantapp.transactions.model.TransactionExportJob;
import com.fimepay.merchantapp.transactions.repository.TransactionExportJobRepository;
import com.fimepay.merchantapp.transactions.service.ExportService;
import com.fimepay.merchantapp.model.Merchant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@Transactional
public class ExportServiceImpl implements ExportService {

    private final TransactionExportJobRepository repo;
    private final ModelMapper mapper;

    public ExportServiceImpl(TransactionExportJobRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public TransactionExportJobDTO scheduleExport(
            UUID merchantId,
            CreateExportJobRequest req) {
        TransactionExportJob job = new TransactionExportJob();
        job.setMerchant(new Merchant(merchantId)); // stub merchant
        job.setFilterJson(req.getFilterJson());
        job.setScheduleCron(req.getScheduleCron());
        job.setDestination(req.getDestination());
        TransactionExportJob saved = repo.save(job);
        return mapper.map(saved, TransactionExportJobDTO.class);
    }

    @Override
    public byte[] runExportNow(UUID jobId) {
        TransactionExportJob job = repo.findById(jobId)
            .orElseThrow(() -> new RuntimeException("Export job not found: " + jobId));
        // stub: return CSV header only
        String csv = "id,amount,currency,status,createdAt\n";
        return csv.getBytes(StandardCharsets.UTF_8);
    }
}
