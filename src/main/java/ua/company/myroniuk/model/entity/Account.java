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
    private Role role;

    // Constructors.
    public Account() {
    }

    private Account(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
    }

    // Getters and setters.
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                role == account.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    // Builder.
    public static class Builder {
        private long id;
        private String login;
        private String password;
        private Role role;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
