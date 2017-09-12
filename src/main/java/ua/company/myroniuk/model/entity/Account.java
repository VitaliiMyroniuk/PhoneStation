package ua.company.myroniuk.model.entity;

/**
 * @author Vitalii Myroniuk
 */
public class Account {
    private long id;
    private String login;
    private String password;
    private boolean isAdmin;

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
        final Account account = (Account) obj;
        if (this.id != account.id) {
            return false;
        }
        if ((this.login == null) ? (account.login != null) : !this.login.equals(account.login)) {
            return false;
        }
        if ((this.password == null) ? (account.password != null) : !this.password.equals(account.password)) {
            return false;
        }
        if (this.isAdmin != account.isAdmin) {
            return false;
        }
        return true;
    }
}
