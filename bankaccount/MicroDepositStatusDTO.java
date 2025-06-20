package com.fimepay.merchantapp.bankaccount;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class MicroDepositStatusDTO {
    private VerificationStatus status;
    private List<BigDecimal> expectedAmounts;
    private List<BigDecimal> enteredAmounts;  // client-submitted amounts
    private Instant verifiedAt;

    // getters & setters
    public VerificationStatus getStatus() { return status; }
    public void setStatus(VerificationStatus status) { this.status = status; }
    public List<BigDecimal> getExpectedAmounts() { return expectedAmounts; }
    public void setExpectedAmounts(List<BigDecimal> expectedAmounts) { this.expectedAmounts = expectedAmounts; }
    public List<BigDecimal> getEnteredAmounts() { return enteredAmounts; }
    public void setEnteredAmounts(List<BigDecimal> enteredAmounts) { this.enteredAmounts = enteredAmounts; }
    public Instant getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(Instant verifiedAt) { this.verifiedAt = verifiedAt; }
}
