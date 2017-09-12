package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Account;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vitalii Myroniuk
 */
public interface AccountDao {

    long addAccount(Connection connection, Account account) throws SQLException;

    Account getAccountByLogin(String login);

    /**
     * The method checks the given login and password.
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with user; <br>
     *         -1 if there is no such login and password in the data base.
     */
    int checkLoginAndPassword(String login, String password);
}
