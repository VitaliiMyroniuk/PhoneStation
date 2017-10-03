package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.*;
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
public class JdbcUserDao implements UserDao {

    private static final String ADD_USER =
            "INSERT INTO users " +
            "(account_id, name, middle_name, surname, phone_number, balance, is_registered, is_blocked) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_BY_ID =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE users.id = ?";

    private static final String GET_USER_BY_LOGIN =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE login = ?";

    private static final String GET_REGISTERED_USER_BY_LOGIN_AND_PASSWORD =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "WHERE login = ? AND password = ? AND is_registered = 1";

    private static final String GET_USER_BY_PHONE_NUMBER =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE phone_number = ?";

    private static final String GET_USER_WITH_UNPAID_INVOICES_BY_ID =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "INNER JOIN invoices ON users.id = user_id " +
            "WHERE users.id = ? AND is_paid = 0";

    private static final String GET_REGISTERED_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "WHERE is_registered = 1 AND role NOT LIKE 'ADMIN'";

    private static final String GET_UNREGISTERED_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE is_registered = 0";

    private static final String GET_DEBTORS =
            "SELECT *, sum(price) AS debt FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id " +
            "INNER JOIN invoices ON users.id = user_id " +
            "WHERE is_paid = 0 GROUP BY user_id ORDER BY debt DESC";

    private static final String GET_USER_COUNT_INFO =
            "SELECT * FROM (SELECT COUNT(*) AS all_users FROM users WHERE phone_number IS NOT NULL) t1 " +
            "INNER JOIN (SELECT COUNT(*) AS new_users FROM users WHERE is_registered = 0) t2 " +
            "INNER JOIN (SELECT count(*) AS debtors FROM " +
                        "(SELECT * FROM invoices WHERE is_paid = 0 GROUP BY user_id) t " +
                        ") t3";

    private static final String UPDATE_USER_BALANCE =
            "UPDATE users SET balance = balance + ? WHERE id = ?";

    private static final String UPDATE_IS_REGISTERED =
            "UPDATE users SET is_registered = 1 WHERE id = ?";

    private static final String UPDATE_IS_BLOCKED =
            "UPDATE users SET is_blocked = ? WHERE id = ?";

    private static final String DELETE_USER =
            "DELETE FROM users where id = ?";

    private static final Logger LOGGER = Logger.getLogger(JdbcUserDao.class);

    private Connection connection;

    JdbcUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long addUser(User user) {
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
        } catch (SQLException e) {
            LOGGER.error("Error during inserting the account into the data base: ", e);
            throw new RuntimeException(e);
        }
        return userId;
    }

    @Override
    public User getUserById(long id) {
        try (PreparedStatement preparedStatement =
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
    public boolean deleteUser(long id) {
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

    @Override
    public User getUserWithUnpaidInvoicesById(long id) {
        User user = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_WITH_UNPAID_INVOICES_BY_ID);
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
            LOGGER.error("Error during getting the user with unpaid invoices by id: ", e);
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
    public boolean updateIsRegistered(long userId) {
        try (PreparedStatement preparedStatement =
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
        try (PreparedStatement preparedStatement =
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
    public User logIn(String login, String password) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_REGISTERED_USER_BY_LOGIN_AND_PASSWORD);
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the registered user by login and password: ", e);
            throw new RuntimeException(e);
        }
        return null;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException {
        return new Account.Builder()
                .setId(resultSet.getLong("accounts.id"))
                .setLogin(resultSet.getString("accounts.login"))
                .setPassword(resultSet.getString("accounts.password"))
                .setRole(Role.valueOf(resultSet.getString("accounts.role").toUpperCase()))
                .build();
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        Account account = createAccount(resultSet);
        return new User.Builder()
                .setAccount(account)
                .setId(resultSet.getLong("users.id"))
                .setName(resultSet.getString("users.name"))
                .setMiddleName(resultSet.getString("users.middle_name"))
                .setSurname(resultSet.getString("users.surname"))
                .setPhoneNumber(resultSet.getString("users.phone_number"))
                .setBalance(resultSet.getInt("users.balance"))
                .setRegistered(resultSet.getBoolean("users.is_registered"))
                .setBlocked(resultSet.getBoolean("users.is_blocked"))
                .build();
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        return new Invoice.Builder()
                .setId(resultSet.getLong("invoices.id"))
                .setDateTime(resultSet.getTimestamp("invoices.date").toLocalDateTime())
                .setDescription(resultSet.getString("invoices.description"))
                .setPrice(resultSet.getInt("invoices.price"))
                .setPaid(resultSet.getBoolean("invoices.is_paid"))
                .build();
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
