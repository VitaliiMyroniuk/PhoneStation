package ua.company.myroniuk.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Vitalii Myroniuk
 */
public class Invoice {
    private long id;
    private LocalDateTime dateTime;
    private String description;
    private int price;
    private boolean isPaid;

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
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Invoice invoice = (Invoice) obj;
        if (this.id != invoice.id) {
            return false;
        }
        if ((this.dateTime == null) ? (invoice.dateTime != null) : !this.dateTime.equals(invoice.dateTime)) {
            return false;
        }
        if ((this.description == null) ? (invoice.description != null) : !this.description.equals(invoice.description)) {
            return false;
        }
        if (this.price != invoice.price) {
            return false;
        }
        if (this.isPaid != invoice.isPaid) {
            return false;
        }
        return true;
    }
}
