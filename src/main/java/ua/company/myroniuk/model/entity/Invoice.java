package ua.company.myroniuk.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The class describes user invoice entity.
 *
 * @author Vitalii Myroniuk
 */
public class Invoice {
    private long id;
    private LocalDateTime dateTime;    // date of the invoice issue.
    private String description;
    private int price;
    private boolean isPaid;

    public Invoice() {
    }

    private Invoice(Builder builder) {
        this.id = builder.id;
        this.dateTime = builder.dateTime;
        this.description = builder.description;
        this.price = builder.price;
        this.isPaid = builder.isPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                price == invoice.price &&
                isPaid == invoice.isPaid &&
                Objects.equals(dateTime, invoice.dateTime) &&
                Objects.equals(description, invoice.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, description, price, isPaid);
    }

    public static class Builder {
        private long id;
        private LocalDateTime dateTime;
        private String description;
        private int price;
        private boolean isPaid;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}
