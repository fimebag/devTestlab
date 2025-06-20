package com.fimepay.merchantapp.repository;

import com.fimepay.merchantapp.model.WebhookSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WebhookSubscriptionRepository extends JpaRepository<WebhookSubscription, UUID> {
    List<WebhookSubscription> findByMerchant_Id(UUID merchantId);
}
