// src/main/java/com/fimepay/merchantapp/crypto/CryptoWalletLinkRequest.java
package com.fimepay.merchantapp.crypto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CryptoWalletLinkRequest {
    @NotBlank
    @Size(min = 3, max = 10)
    private String currency;

    @NotBlank
    @Size(min = 3, max = 50)
    private String network;

    @NotBlank
    @Size(min = 10, max = 255)
    private String address;

    // getters & setters
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getNetwork() { return network; }
    public void setNetwork(String network) { this.network = network; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
