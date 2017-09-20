package ua.company.myroniuk.model.entity;

import java.util.List;
import java.util.Objects;

/**
 * The class describes the user entity.
 *
 * @author Vitalii Myroniuk
 */
public class User {
    /**
     * Id of the user.
     */
    private long id;

    /**
     * Account of the user.
     */
    private Account account;

    /**
     * Name of the user.
     */
    private String name;

    /**
     * Middle name of the user.
     */
    private String middleName;

    /**
     * Surname of the user.
     */
    private String surname;

    /**
     * Phone number of the user.
     */
    private String phoneNumber;

    /**
     * Balance of the user.
     */
    private int balance;

    /**
     * List of active user services.
     */
    private List<Service> services;

    /**
     * List of user invoices.
     */
    private List<Invoice> invoices;

    /**
     * Status of the user registration.
     */
    private boolean isRegistered;

    /**
     * Status of the user block.
     */
    private boolean isBlocked;

    // Getters and setters.
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
        if (!(o instanceof User)) return false;
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
        return Objects.hash(phoneNumber);
    }
}
