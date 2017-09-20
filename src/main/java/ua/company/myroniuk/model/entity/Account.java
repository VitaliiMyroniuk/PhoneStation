package ua.company.myroniuk.model.entity;

import java.util.Objects;

/**
 * The class describes the user account entity.
 * It contains security parameters for the user authentication.
 *
 * @author Vitalii Myroniuk
 */
public class Account {
    /**
     * Id of the account.
     */
    private long id;

    /**
     * Login of the user.
     */
    private String login;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Role of the user.
     */
    private boolean isAdmin;

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id &&
                isAdmin == account.isAdmin &&
                Objects.equals(login, account.login) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
