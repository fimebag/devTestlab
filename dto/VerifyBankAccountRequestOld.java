package com.fimepay.merchantapp.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class VerifyBankAccountRequestOld {
    @NotNull private BigDecimal deposit1;
    @NotNull private BigDecimal deposit2;
    // getters/setters

    public BigDecimal getDeposit1() {
        return deposit1;
    }

    public void setDeposit1(BigDecimal deposit1) {
        this.deposit1 = deposit1;
    }

    public BigDecimal getDeposit2() {
        return deposit2;
    }

    public void setDeposit2(BigDecimal deposit2) {
        this.deposit2 = deposit2;
    }
}
