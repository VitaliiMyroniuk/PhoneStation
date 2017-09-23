package ua.company.myroniuk.dao.impl;

import org.apache.log4j.Logger;
import ua.company.myroniuk.dao.*;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Role;
import ua.company.myroniuk.model.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private final String ADD_USER =
            "INSERT INTO users " +
            "(account_id, name, middle_name, surname, phone_number, balance, is_registered, is_blocked) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private final String GET_USER_BY_ID =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE users.id = ?";

    private final String GET_USER_WITH_INVOICES_BY_ID =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "INNER JOIN invoices ON users.id = user_id " +
            "WHERE users.id = ? AND is_paid = 0";

    private final String GET_USER_BY_LOGIN =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE login = ?";

    private final String GET_USER_BY_PHONE_NUMBER =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE phone_number = ?";

    private final String GET_REGISTERED_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE is_registered = 1";

    private final String GET_UNREGISTERED_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE is_registered = 0";

    private final String GET_DEBTORS =
            "SELECT *, sum(price) AS debt FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "INNER JOIN invoices ON users.id = user_id " +
            "WHERE is_paid = 0 GROUP BY user_id ORDER BY debt DESC";

    private final String GET_USER_COUNT_INFO =
            "SELECT * FROM (SELECT COUNT(*) AS all_users FROM users) t1" +
            "INNER JOIN (SELECT COUNT(*) AS new_users FROM users WHERE is_registered = 0) t2" +
            "INNER JOIN (SELECT count(*) AS debtors FROM " +
                        "(SELECT * FROM invoices WHERE is_paid = 0 GROUP BY user_id) t " +
                        ") t3";

    private final String UPDATE_USER_BALANCE =
            "UPDATE users SET balance = balance + ? WHERE id = ?";

    private final String UPDATE_IS_REGISTERED =
            "UPDATE users SET is_registered = 1 WHERE id = ?";

    private final String UPDATE_IS_BLOCKED =
            "UPDATE users SET is_blocked = ? WHERE id = ?";

    private final String DELETE_USER =
            "DELETE FROM users where id = ?";

    private UserDaoImpl() {
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addUser(Connection connection, User user) throws SQLException {
        long userId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            setStatementParameters(preparedStatement, user);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userId = resultSet.getLong(1);
            }
        }
        return userId;
    }

    @Override
    public User getUserById(long id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the user by id: ", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserWithInvoicesById(long id) {
        User user = null;
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_WITH_INVOICES_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Invoice> invoices = new ArrayList<>();
            while (resultSet.next()) {
                if (user == null) {
                    user = createUser(resultSet);
                }
                Invoice invoice = createInvoice(resultSet);
                invoices.add(invoice);
            }
            if (user != null) {
                user.setInvoices(invoices);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the user with invoices by id: ", e);
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_BY_LOGIN);
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the user by login: ", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_BY_PHONE_NUMBER);
        ) {
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the user by phone number: ", e);
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public List<User> getRegisteredUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_REGISTERED_USERS);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the list of registered users: ", e);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<User> getUnregisteredUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_UNREGISTERED_USERS);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the list of unregistered users: ", e);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public List<User> getDebtors() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_DEBTORS);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the list of debtors: ", e);
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public int[] getUserCountInfo() {
        int[] userCountInfo = new int[3];
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_COUNT_INFO);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userCountInfo[0] = resultSet.getInt("all_users");
                userCountInfo[1] = resultSet.getInt("new_users");
                userCountInfo[2] = resultSet.getInt("debtors");
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the user count info: ", e);
            throw new RuntimeException(e);
        }
        return userCountInfo;
    }

    @Override
    public boolean updateBalance(long userId, int sum) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_USER_BALANCE);
        ) {
            preparedStatement.setInt(1, sum);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during updating the user balance: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateBalance(Connection connection, long userId, int sum) throws SQLException {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_USER_BALANCE);
        ) {
            preparedStatement.setInt(1, sum);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean updateIsRegistered(long userId) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_IS_REGISTERED);
        ) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during updating the user registration status: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateIsBlocked(long userId, boolean isBlocked) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_IS_BLOCKED);
        ) {
            preparedStatement.setBoolean(1, isBlocked);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during updating the user block status: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(Connection connection, long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_USER);
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the user: ", e);
            throw new RuntimeException(e);
        }
    }

    private Account createAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("accounts.id"));
        account.setLogin(resultSet.getString("accounts.login"));
        account.setPassword(resultSet.getString("accounts.password"));
        account.setRole(Role.valueOf(resultSet.getString("accounts.role").toUpperCase()));
        return account;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        Account account = createAccount(resultSet);
        User user = new User();
        user.setAccount(account);
        user.setId(resultSet.getLong("users.id"));
        user.setName(resultSet.getString("users.name"));
        user.setMiddleName(resultSet.getString("users.middle_name"));
        user.setSurname(resultSet.getString("users.surname"));
        user.setPhoneNumber(resultSet.getString("users.phone_number"));
        user.setBalance(resultSet.getInt("users.balance"));
        user.setRegistered(resultSet.getBoolean("users.is_registered"));
        user.setBlocked(resultSet.getBoolean("users.is_blocked"));
        return user;
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong("invoices.id"));
        invoice.setDateTime(resultSet.getTimestamp("invoices.date").toLocalDateTime());
        invoice.setDescription(resultSet.getString("invoices.description"));
        invoice.setPrice(resultSet.getInt("invoices.price"));
        invoice.setPaid(resultSet.getBoolean("invoices.is_paid"));
        return invoice;
    }

    private void setStatementParameters(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setLong(1, user.getAccount().getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getMiddleName());
        preparedStatement.setString(4, user.getSurname());
        preparedStatement.setString(5, user.getPhoneNumber());
        preparedStatement.setInt(6, user.getBalance());
        preparedStatement.setBoolean(7, user.isRegistered());
        preparedStatement.setBoolean(8, user.isBlocked());
    }
}
