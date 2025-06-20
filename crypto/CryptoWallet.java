// src/main/java/com/fimepay/merchantapp/crypto/CryptoWallet.java
package com.fimepay.merchantapp.crypto;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "crypto_wallets")
public class CryptoWallet {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 10)
    private String currency;   // e.g. "BTC", "ETH", "USDC"

    @Column(nullable = false)
    private String network;    // e.g. "Bitcoin Mainnet", "Ethereum Mainnet"

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private com.fimepay.merchantapp.bankaccount.VerificationStatus status;

    @Column(name = "linked_at", nullable = false)
    private Instant linkedAt;

    @Column(length = 100)
    private String nickname;

    // getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getMerchantId() { return merchantId; }
    public void setMerchantId(UUID merchantId) { this.merchantId = merchantId; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getNetwork() { return network; }
    public void setNetwork(String network) { this.network = network; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public com.fimepay.merchantapp.bankaccount.VerificationStatus getStatus() { return status; }
    public void setStatus(com.fimepay.merchantapp.bankaccount.VerificationStatus status) { this.status = status; }
    public Instant getLinkedAt() { return linkedAt; }
    public void setLinkedAt(Instant linkedAt) { this.linkedAt = linkedAt; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
