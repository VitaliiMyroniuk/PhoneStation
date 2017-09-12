package ua.company.myroniuk.model.entity;

import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class User {
    private long id;                // TODO find what is better long or Long?
    private Account account;
    private String name;
    private String middleName;
    private String surname;
    private String phoneNumber;
    private int balance;
    private List<Service> services;  // TODO answer why we use List (ArrayLis) here?
    private List<Invoice> invoices;
    private boolean isRegistered;
    private boolean isBlocked;

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
        final User user = (User) obj;  // TODO find out the presents of the final modifier
        if (this.id != user.id) {
            return false;
        }
        if ((this.name == null) ? (user.name != null) : !this.name.equals(user.name)) {
            return false;
        }
        if ((this.middleName == null) ? (user.middleName != null) : !this.middleName.equals(user.middleName)) {
            return false;
        }
        if ((this.surname == null) ? (user.surname != null) : !this.surname.equals(user.surname)) {
            return false;
        }
        if ((this.phoneNumber == null) ? (user.phoneNumber != null) : !this.phoneNumber.equals(user.phoneNumber)) {
            return false;
        }
        if (this.balance != user.balance) {
            return false;
        }
        // TODO find if this overriding is correct?
        if (this.services != user.services && (this.services == null || !this.services.equals(user.services))) {
            return false;
        }
        if (this.invoices != user.invoices && (this.invoices == null || !this.invoices.equals(user.invoices))) {
            return false;
        }
        if (this.isRegistered != user.isRegistered) {
            return false;
        }
        if (this.isBlocked != user.isBlocked) {
            return false;
        }
        return true;
    }
}
