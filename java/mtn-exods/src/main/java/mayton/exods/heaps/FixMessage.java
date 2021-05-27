package mayton.exods.heaps;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class FixMessage implements Comparable<FixMessage>, Serializable {

    private BigDecimal amount;

    private String messageBody;

    public FixMessage(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(@NotNull FixMessage fixMessage) {
        return amount.compareTo(fixMessage.amount);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FixMessage{" +
                "amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FixMessage that = (FixMessage) o;
        return Objects.equals(amount, that.amount);
    }

    public String getMessageBody() {
        return messageBody;
    }


}
