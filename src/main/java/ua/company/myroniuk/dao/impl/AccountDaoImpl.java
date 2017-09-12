package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.AccountDao;
import ua.company.myroniuk.dao.DBManager;
import ua.company.myroniuk.model.entity.Account;

import java.sql.*;

/**
 * @author Vitalii Myroniuk
 */
public class AccountDaoImpl implements AccountDao {
    private final String ADD_ACCOUNT =
            "INSERT INTO accounts (id, login, password, is_admin) VALUES (null, ?, ?, ?)";
    private final String GET_ACCOUNT_BY_LOGIN =
            "SELECT * FROM accounts WHERE login = ?";
    private final String GET_DATA_BY_LOGIN_AND_PASSWORD =
            "SELECT is_admin, is_registered FROM accounts " +
            "INNER JOIN users ON accounts.id = account_id WHERE login = ? AND password = ?";

    private AccountDaoImpl() {
    }

    private static class SingletonHolder {
        private static final AccountDaoImpl INSTANCE = new AccountDaoImpl();
    }

    public static AccountDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addAccount(Connection connection, Account account) throws SQLException {
        long accountId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, account.getLogin());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setBoolean(3, account.isAdmin());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                accountId = resultSet.getLong(1);
            }
        }
        return accountId;
    }

    @Override
    public Account getAccountByLogin(String login) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_LOGIN);
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createAccount(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int checkLoginAndPassword(String login, String password) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DATA_BY_LOGIN_AND_PASSWORD);
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getBoolean("is_admin")) {
                    return 1;
                } else if (resultSet.getBoolean("is_registered")){
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setLogin(resultSet.getString("login"));
        account.setPassword(resultSet.getString("password"));
        account.setAdmin(resultSet.getBoolean("is_admin"));
        return account;
    }
}
