//package ua.company.myroniuk.dao.impl.rubbish;
//
//import ua.company.myroniuk.dao.DBManager;
//import ua.company.myroniuk.dao.UserDao;
//import ua.company.myroniuk.model.entity.Account;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Vitalii Myroniuk
// */
//public class UserDaoImpl implements UserDao {
//    private final String ADD_USER = "INSERT INTO users " +
//            "(id, sim_card_id, name, surname, passport, login, password, is_admin) " +
//            "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
//    private final String GET_USER_BY_LOGIN = "SELECT * FROM users " +
//            "INNER JOIN sim_cards ON sim_card_id = sim_cards.id WHERE login = ?";
//    private final String GET_UNREGISTERED_USERS = "SELECT * FROM users " +
//            "INNER JOIN sim_cards ON sim_card_id = sim_cards.id WHERE is_registered = false";
//    private final String GET_DEBTORS = "SELECT * FROM users " +
//            "INNER JOIN sim_cards ON sim_card_id = sim_cards.id WHERE balance < 0";
//    private final String GET_ALL_USERS = "SELECT * FROM users";
//    private final String UPDATE_USER = "UPDATE users SET " +
//            "name = ?, surname = ?, passport = ?, login = ?, password = ?, is_admin = ? WHERE id = ?";
//    private final String DELETE_USER = "DELETE FROM users WHERE id = ?";
//
//    private UserDaoImpl() {
//    }
//
//    private static class SingletonHolder {
//        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
//    }
//
//    public static UserDaoImpl getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    @Override
//    public long addUser(Account user) {
//        long userId = 0;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DBManager.getConnection();
//            connection.setAutoCommit(false);
//            long simCardId = SimCardDaoImpl.getInstance().addSimCard(connection, user.getSimCard());
//            preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(1, simCardId);
//            preparedStatement.setString(2, user.getName());
//            preparedStatement.setString(3, user.getSurname());
//            preparedStatement.setString(4, user.getPassport());
//            preparedStatement.setString(5, user.getLogin());
//            preparedStatement.setString(6, user.getPassword());
//            preparedStatement.setBoolean(7, user.isAdmin());
//            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet != null && resultSet.next()) {
//                userId = resultSet.getLong(1);
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//        } finally {
//            DBManager.closeConnection(connection);
//            DBManager.closeStatement(preparedStatement);
//        }
//        return userId;
//    }
//
//    @Override
//    public Account getUser(String login) {
//        Account user = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DBManager.getConnection();
//            preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
//            preparedStatement.setString(1, login);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet != null && resultSet.next()) {
//                user = new Account();
//                user.setId(resultSet.getLong("users.id"));
//                user.setSimCardId(resultSet.getLong("users.sim_card_id"));
//                user.setName(resultSet.getString("users.name"));
//                user.setSurname(resultSet.getString("users.surname"));
//                user.setPassport(resultSet.getString("users.passport"));
//                user.setLogin(resultSet.getString("users.login"));
//                user.setPassword(resultSet.getString("users.password"));
//                user.setAdmin(resultSet.getBoolean("users.is_admin"));
//                SimCard simCard = new SimCard();
//                simCard.setId(resultSet.getLong("sim_cards.id"));
//                simCard.setNumber(resultSet.getString("sim_cards.number"));
//                simCard.setBalance(resultSet.getInt("sim_cards.balance"));
//                simCard.setRegistered(resultSet.getBoolean("sim_cards.is_registered"));
//                simCard.setBlocked(resultSet.getBoolean("sim_cards.is_blocked"));
//                user.setSimCard(simCard);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.closeConnection(connection);
//            DBManager.closeStatement(preparedStatement);
//        }
//        return user;
//    }
//
//    @Override
//    public List<Account> getUnregisteredUsers() {
//        List<Account> users = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DBManager.getConnection();
//            preparedStatement = connection.prepareStatement(GET_UNREGISTERED_USERS);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet != null && resultSet.next()) {
//                Account user = new Account();
//                user.setId(resultSet.getLong("users.id"));
//                user.setSimCardId(resultSet.getLong("users.sim_card_id"));
//                user.setName(resultSet.getString("users.name"));
//                user.setSurname(resultSet.getString("users.surname"));
//                user.setPassport(resultSet.getString("users.passport"));
//                user.setLogin(resultSet.getString("users.login"));
//                user.setPassword(resultSet.getString("users.password"));
//                user.setAdmin(resultSet.getBoolean("users.is_admin"));
//                SimCard simCard = new SimCard();
//                simCard.setId(resultSet.getLong("sim_cards.id"));
//                simCard.setNumber(resultSet.getString("sim_cards.number"));
//                simCard.setBalance(resultSet.getInt("sim_cards.balance"));
//                simCard.setRegistered(resultSet.getBoolean("sim_cards.is_registered"));
//                simCard.setBlocked(resultSet.getBoolean("sim_cards.is_blocked"));
//                user.setSimCard(simCard);
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.closeConnection(connection);
//            DBManager.closeStatement(preparedStatement);
//        }
//        return users;
//    }
//
//    @Override
//    public List<Account> getDebtors() {
//        List<Account> users = new ArrayList<>();
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = DBManager.getConnection();
//            preparedStatement = connection.prepareStatement(GET_DEBTORS);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet != null && resultSet.next()) {
//                Account user = new Account();
//                user.setId(resultSet.getLong("users.id"));
//                user.setSimCardId(resultSet.getLong("users.sim_card_id"));
//                user.setName(resultSet.getString("users.name"));
//                user.setSurname(resultSet.getString("users.surname"));
//                user.setPassport(resultSet.getString("users.passport"));
//                user.setLogin(resultSet.getString("users.login"));
//                user.setPassword(resultSet.getString("users.password"));
//                user.setAdmin(resultSet.getBoolean("users.is_admin"));
//                SimCard simCard = new SimCard();
//                simCard.setId(resultSet.getLong("sim_cards.id"));
//                simCard.setNumber(resultSet.getString("sim_cards.number"));
//                simCard.setBalance(resultSet.getInt("sim_cards.balance"));
//                simCard.setRegistered(resultSet.getBoolean("sim_cards.is_registered"));
//                simCard.setBlocked(resultSet.getBoolean("sim_cards.is_blocked"));
//                user.setSimCard(simCard);
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBManager.closeConnection(connection);
//            DBManager.closeStatement(preparedStatement);
//        }
//        return users;
//    }
//}