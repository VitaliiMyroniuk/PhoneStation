package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.*;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserDaoImpl implements UserDao {

    private final String ADD_USER =
            "INSERT INTO users " +
            "(account_id, name, middle_name, surname, phone_number, balance, is_registered, is_blocked) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private final String GET_USER_BY_ID =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE users.id = ?";

    private final String GET_USER_BY_LOGIN =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE login = ?";

    private final String GET_USER_BY_PHONE_NUMBER =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE phone_number = ?";

    private final String GET_ALL_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id";

    private final String GET_UNREGISTERED_USERS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE is_registered = false";

    private final String GET_DEBTORS =
            "SELECT * FROM users " +
            "INNER JOIN accounts ON account_id = accounts.id WHERE balance < 0";

    private final String GET_USER_COUNT_INFO =
            "SELECT * FROM (SELECT COUNT(*) AS all_users FROM users) t1" +
            "INNER JOIN (SELECT COUNT(*) AS new_users FROM users WHERE is_registered = 0) t2" +
            "INNER JOIN (SELECT COUNT(*) AS debtors FROM .users WHERE balance < 0) t3";

    private final String UPDATE_USER_BALANCE =
            "UPDATE users SET balance = balance + ? WHERE id = ?";

    private final String UPDATE_IS_REGISTERED =
            "UPDATE users SET is_registered = 1 WHERE id = ?";

    private final String UPDATE_IS_BLOCKED =
            "UPDATE users SET is_blocked = ? WHERE id = ?";

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
            if(resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByLogin(String login) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_BY_LOGIN);
        ) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if(resultSet.next()) {
                return createUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_ALL_USERS);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            while(resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            while(resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            if(resultSet.next()) {
                userCountInfo[0] = resultSet.getInt("all_users");
                userCountInfo[1] = resultSet.getInt("new_users");
                userCountInfo[2] = resultSet.getInt("debtors");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return false;
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
            e.printStackTrace();
        }
        return false;
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
            e.printStackTrace();
        }
        return false;
    }

    private Account createAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("accounts.id"));
        account.setLogin(resultSet.getString("accounts.login"));
        account.setPassword(resultSet.getString("accounts.password"));
        account.setAdmin(resultSet.getBoolean("accounts.is_admin"));
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
