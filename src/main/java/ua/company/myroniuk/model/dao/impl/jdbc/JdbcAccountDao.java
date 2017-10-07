package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.AccountDao;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Role;
import java.sql.*;

/**
 * The {@code JdbcAccountDao} class is a JDBC implementation of the {@code AccountDao} interface.
 *
 * @author Vitalii Myroniuk
 */
public class JdbcAccountDao implements AccountDao {

    private static final String ADD_ACCOUNT =
            "INSERT INTO accounts (login, password, role) VALUES (?, ?, ?)";

    private static final String GET_ACCOUNT_BY_LOGIN =
            "SELECT * FROM accounts WHERE login = ?";

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
                     connection.prepareStatement(ADD_ACCOUNT, Statement.RETURN_GENERATED_KEYS)) {
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
        Account account = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_ACCOUNT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = createAccount(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting an account by login: ", e);
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public boolean deleteAccount(long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_ACCOUNT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the account: ", e);
            throw new RuntimeException(e);
        }
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
