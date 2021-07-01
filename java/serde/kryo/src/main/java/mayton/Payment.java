package mayton;

import java.math.BigDecimal;

public class Payment {

    private String sourceCardId;
    private String destCardId;
    private BigDecimal amount;

    public String getSourceCardId() {
        return sourceCardId;
    }

    public void setSourceCardId(String sourceCardId) {
        this.sourceCardId = sourceCardId;
    }

    public String getDestCardId() {
        return destCardId;
    }

    public void setDestCardId(String destCardId) {
        this.destCardId = destCardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
