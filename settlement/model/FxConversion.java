package com.fimepay.merchantapp.settlement.model;

import com.fimepay.merchantapp.model.Merchant;
import com.fimepay.merchantapp.model.Wallet;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fx_conversions")
public class FxConversion {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name="merchant_id")
    private Merchant merchant;

    @ManyToOne(optional = false)
    @JoinColumn(name="from_wallet_id")
    private Wallet fromWallet;

    @ManyToOne(optional = false)
    @JoinColumn(name="to_wallet_id")
    private Wallet toWallet;

    @Column(nullable=false, precision=19, scale=4)
    private BigDecimal amountIn;

    @Column(nullable=false, precision=19, scale=4)
    private BigDecimal amountOut;

    @Column(nullable=false, precision=19, scale=6)
    private BigDecimal rate;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ConversionStatus status = ConversionStatus.PENDING;

    @Column(nullable=false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Wallet getFromWallet() {
        return fromWallet;
    }

    public void setFromWallet(Wallet fromWallet) {
        this.fromWallet = fromWallet;
    }

    public Wallet getToWallet() {
        return toWallet;
    }

    public void setToWallet(Wallet toWallet) {
        this.toWallet = toWallet;
    }

    public BigDecimal getAmountIn() {
        return amountIn;
    }

    public void setAmountIn(BigDecimal amountIn) {
        this.amountIn = amountIn;
    }

    public BigDecimal getAmountOut() {
        return amountOut;
    }

    public void setAmountOut(BigDecimal amountOut) {
        this.amountOut = amountOut;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public ConversionStatus getStatus() {
        return status;
    }

    public void setStatus(ConversionStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // getters & setters...
}

