package ua.company.myroniuk.model.entity;

import java.util.List;
import java.util.Objects;

/**
 * The class describes the user entity.
 *
 * @author Vitalii Myroniuk
 */
public class User {
    private long id;
    private Account account;
    private String name;
    private String middleName;
    private String surname;
    private String phoneNumber;
    private int balance;
    private List<Service> services;
    private List<Invoice> invoices;
    private boolean isRegistered;
    private boolean isBlocked;

    public User() {
    }

    private User(Builder builder) {
        this.account = builder.account;
        this.id = builder.id;
        this.name = builder.name;
        this.middleName = builder.middleName;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.balance = builder.balance;
        this.services = builder.services;
        this.invoices = builder.invoices;
        this.isRegistered = builder.isRegistered;
        this.isBlocked = builder.isBlocked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                balance == user.balance &&
                isRegistered == user.isRegistered &&
                isBlocked == user.isBlocked &&
                Objects.equals(account, user.account) &&
                Objects.equals(name, user.name) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(services, user.services) &&
                Objects.equals(invoices, user.invoices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, name, middleName, surname, phoneNumber,
                            balance, services, invoices, isRegistered, isBlocked);
    }

    public static class Builder {
        private long id;
        private Account account;
        private String name;
        private String middleName;
        private String surname;
        private String phoneNumber;
        private int balance;
        private List<Service> services;
        private List<Invoice> invoices;
        private boolean isRegistered;
        private boolean isBlocked;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setBalance(int balance) {
            this.balance = balance;
            return this;
        }

        public Builder setServices(List<Service> services) {
            this.services = services;
            return this;
        }

        public Builder setInvoices(List<Invoice> invoices) {
            this.invoices = invoices;
            return this;
        }

        public Builder setRegistered(boolean isRegistered) {
            this.isRegistered = isRegistered;
            return this;
        }

        public Builder setBlocked(boolean isBlocked) {
            this.isBlocked = isBlocked;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
