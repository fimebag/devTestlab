package com.fimepay.merchantapp.idempotency;

// src/main/java/com/fimepay/merchantapp/idempotency/IdempotencyCleanupTask.java

import com.fimepay.merchantapp.idempotency.repository.IdempotencyKeyRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;

@Component
public class IdempotencyCleanupTask {

    private static final Logger logger = LoggerFactory.getLogger(IdempotencyCleanupTask.class);
    private final IdempotencyKeyRepository repo;

    public IdempotencyCleanupTask(IdempotencyKeyRepository repo) {
        this.repo = repo;
    }

    /**
     * Runs every day at 2 AM to delete records whose expiresAt has passed.
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void purgeExpiredKeys() {
        LocalDateTime now = LocalDateTime.now();
        int deleted = repo.deleteAllByExpiresAtBefore(now);
        // optionally log: logger.info("Purged {} expired idempotency keys", deleted);
        logger.info("Purged {} expired idempotency keys", deleted);
    }
}
