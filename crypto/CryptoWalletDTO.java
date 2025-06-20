// src/main/java/com/fimepay/merchantapp/crypto/CryptoWalletDTO.java
package com.fimepay.merchantapp.crypto;

import java.time.Instant;
import java.util.UUID;
import com.fimepay.merchantapp.bankaccount.VerificationStatus;

public class CryptoWalletDTO {
    private UUID id;
    private String currency;
    private String network;
    private String address;
    private VerificationStatus status;
    private Instant linkedAt;
    private String nickname;

    // getters & setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getNetwork() { return network; }
    public void setNetwork(String network) { this.network = network; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public VerificationStatus getStatus() { return status; }
    public void setStatus(VerificationStatus status) { this.status = status; }
    public Instant getLinkedAt() { return linkedAt; }
    public void setLinkedAt(Instant linkedAt) { this.linkedAt = linkedAt; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
}
