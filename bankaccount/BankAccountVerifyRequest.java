package com.fimepay.merchantapp.bankaccount;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class BankAccountVerifyRequest {
    @NotNull
    private List<BigDecimal> amounts;

    // getters & setters
    public List<BigDecimal> getAmounts() { return amounts; }
    public void setAmounts(List<BigDecimal> amounts) { this.amounts = amounts; }
}
