package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.DBConnector;
import ua.company.myroniuk.dao.UserDao;
import ua.company.myroniuk.model.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserDaoImpl implements UserDao {
    private final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String ADD_USER = "INSERT INTO users (id, name, surname, passport, login, password, is_admin) " +
                                    "VALUES (null, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_USER = "UPDATE users SET name = ?, surname = ?, passport = ?, login = ?, " +
                                       "password = ?, is_admin = ? WHERE id = ?";
    private final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    private UserDaoImpl() {
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassport());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, user.isAdmin());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                Long id = resultSet.getLong(1);
                System.out.println(id);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(Long id) {
        User user = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("surname"));
                user.setName(resultSet.getString("passport"));
                user.setName(resultSet.getString("login"));
                user.setName(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUser(String login) {
        User user = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user = new User();

//                Long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                String surname = resultSet.getString("surname");
//                String passport = resultSet.getString("passport");
//                String login1 = resultSet.getString("login");
//                String password = resultSet.getString("password");
//                boolean isAdmin = resultSet.getBoolean("is_admin");
//
//                System.out.println(id);
//                System.out.println(name);
//                System.out.println(surname);
//                System.out.println(passport);
//                System.out.println(login1);
//                System.out.println(password);
//                System.out.println(isAdmin);

//                user.setId(id);
//                user.setName(name);
//                user.setSurname(surname);
//                user.setPassport(passport);
//                user.setLogin(login1);
//                user.setPassword(password);
//                user.setAdmin(isAdmin);

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPassport(resultSet.getString("passport"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setName(resultSet.getString("surname"));
                user.setName(resultSet.getString("passport"));
                user.setName(resultSet.getString("login"));
                user.setName(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassport());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setBoolean(6, user.isAdmin());
            preparedStatement.setLong(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Long id) {
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
