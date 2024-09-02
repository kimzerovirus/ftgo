package me.kzv.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.math.BigDecimal;

public class Money {
    private BigDecimal amount;

    public static Money ZERO = new Money();

    private Money() { }

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money(Integer amount) {
        this.amount = new BigDecimal(amount);
    }

    public Money(String amount) {
        this.amount = new BigDecimal(amount);
    }
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Money rhs = (Money) o;

        return new EqualsBuilder()
                .append(amount, rhs.amount)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("amount", amount)
                .toString();
    }

    public Money add(Money delta) {
        return new Money(amount.add(delta.amount));
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public Money multiply(int x) {
        return new Money(amount.multiply(new BigDecimal(x)));
    }
}
