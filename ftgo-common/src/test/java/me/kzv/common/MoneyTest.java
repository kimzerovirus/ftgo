package me.kzv.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {
    private final int M1_AMOUNT = 10;
    private final int M2_AMOUNT = 15;

    private Money m1 = new Money(M1_AMOUNT);
    private Money m2 = new Money(M2_AMOUNT);

    @Test
    public void shouldReturnAsString() {
        assertThat(new Money(M1_AMOUNT).getAmount().toString()).isEqualTo(Integer.toString(M1_AMOUNT));
    }

    @Test
    public void shouldCompare() {
        assertThat(m2.isGreaterThanOrEqual(m2)).isTrue();
        assertThat(m2.isGreaterThanOrEqual(m1)).isTrue();
        assertThat(m1.isGreaterThanOrEqual(m2)).isFalse();
    }

    @Test
    public void shouldAdd() {
        assertThat(m1.add(m2)).isEqualTo(new Money(M1_AMOUNT + M2_AMOUNT));
    }

    @Test
    public void shouldMultiply() {
        int multiplier = 12;
        assertThat(m2.multiply(multiplier)).isEqualTo(new Money(M2_AMOUNT * multiplier));
    }
}