package com.fimepay.merchantapp.settlement.service.impl;

import com.fimepay.merchantapp.settlement.dto.ReconciliationReportDTO;
import com.fimepay.merchantapp.settlement.model.ReconciliationReport;
import com.fimepay.merchantapp.settlement.repository.ReconciliationReportRepository;
import com.fimepay.merchantapp.settlement.service.ReconciliationService;
import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.repository.MerchantRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.*;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class ReconciliationServiceImpl implements ReconciliationService {

    private final ReconciliationReportRepository repo;
    private final MerchantRepository merchantRepo;
    private final ModelMapper mapper;

    public ReconciliationServiceImpl(ReconciliationReportRepository repo,
                                     MerchantRepository merchantRepo,
                                     ModelMapper mapper) {
        this.repo = repo;
        this.merchantRepo = merchantRepo;
        this.mapper = mapper;
    }

    @Override
    public ReconciliationReportDTO generateReport(UUID merchantId,
                                                  LocalDate start,
                                                  LocalDate end) {
        Merchant m = merchantRepo.findById(merchantId)
            .orElseThrow(() -> new RuntimeException("Merchant not found"));

        // stub: write empty CSV to temp file
        Path p;
        try {
            p = Files.createTempFile("recon-", ".csv");
            Files.write(p, "date,wallet,amount,tag\n".getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ReconciliationReport rpt = new ReconciliationReport();
        rpt.setMerchant(m);
        rpt.setPeriodStart(start);
        rpt.setPeriodEnd(end);
        rpt.setReportPath(p.toString());
        ReconciliationReport saved = repo.save(rpt);
        return mapper.map(saved, ReconciliationReportDTO.class);
    }

    @Override
    public Resource downloadReport(UUID reportId) {
        ReconciliationReport rpt = repo.findById(reportId)
            .orElseThrow(() -> new RuntimeException("Report not found"));
        Path p = Paths.get(rpt.getReportPath());
        return new FileSystemResource(p);
    }
}
