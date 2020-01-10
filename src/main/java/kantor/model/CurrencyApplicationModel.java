package kantor.model;

import kantor.office.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrencyApplicationModel {
    private BigDecimal result;
    private BigDecimal margin;
    private BigDecimal rate;
    private BigDecimal amount;
    private Currency from, to;
    private LocalDate date;

    public CurrencyApplicationModel(Builder builder) {
        this.result = builder.result;
        this.margin = builder.margin;
        this.rate = builder.rate;
        this.amount = builder.amount;
        this.from = builder.from;
        this.to = builder.to;
        this.date = builder.date;
    }

    public BigDecimal getResult() {
        return result;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static class Builder {
        private BigDecimal result;
        private BigDecimal margin;
        private BigDecimal rate;
        private BigDecimal amount;
        private Currency from, to;
        private LocalDate date;

        public Builder result(BigDecimal result) {
            this.result = result;
            return this;
        }

        public Builder margin(BigDecimal margin) {
            this.margin = margin;
            return this;
        }

        public Builder rate(BigDecimal rate) {
            this.rate = rate;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder from(Currency from) {
            this.from = from;
            return this;
        }

        public Builder to(Currency to) {
            this.to = to;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public CurrencyApplicationModel build() {
            return new CurrencyApplicationModel(this);
        }
    }
}
