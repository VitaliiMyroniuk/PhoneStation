package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.AccountDao;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Role;
import java.sql.*;

/**
 * @author Vitalii Myroniuk
 */
public class JdbcAccountDao implements AccountDao {

    private static final String ADD_ACCOUNT =
            "INSERT INTO accounts (login, password, role) VALUES (?, ?, ?)";

    private static final String GET_ACCOUNT_BY_LOGIN =
            "SELECT * FROM accounts WHERE login = ?";

    private static final String GET_DATA_BY_LOGIN_AND_PASSWORD =
            "SELECT role, is_registered FROM accounts " +
            "INNER JOIN users ON accounts.id = account_id WHERE login = ? AND password = ?";

    private static final String DELETE_ACCOUNT =
            "DELETE FROM accounts where id = ?";

    private static final Logger LOGGER = Logger.getLogger(JdbcAccountDao.class);

    private Connection connection;

    JdbcAccountDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long addAccount(Account account) {
        long accountId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
        ) {
            setStatementParameters(preparedStatement, account);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                accountId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during inserting the user into the data base: ", e);
            throw new RuntimeException(e);
        }
        return accountId;
    }

    @Override
    public Account getAccountByLogin(String login) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_ACCOUNT_BY_LOGIN);
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createAccount(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting an account by login: ", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteAccount(long accountId) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_ACCOUNT);
        ) {
            preparedStatement.setLong(1, accountId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the account: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkLoginAndPassword(String login, String password) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_DATA_BY_LOGIN_AND_PASSWORD);
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if ("admin".equalsIgnoreCase(resultSet.getString("role"))) {
                    return 1;
                } else if (resultSet.getBoolean("is_registered")){
                    return 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Error during checking login and password: ", e);
            throw new RuntimeException(e);
        }
        return -1;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException {
        return new Account.Builder()
                .setId(resultSet.getLong("id"))
                .setLogin(resultSet.getString("login"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.valueOf(resultSet.getString("role").toUpperCase()))
                .build();
    }

    private void setStatementParameters(PreparedStatement preparedStatement, Account account) throws SQLException {
        preparedStatement.setString(1, account.getLogin());
        preparedStatement.setString(2, account.getPassword());
        preparedStatement.setString(3, account.getRole().toString());
    }
}
