package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.*;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserDaoImpl implements UserDao {
    private final String ADD_USER =
            "INSERT INTO users " +
            "(id, account_id, name, middle_name, surname, phone_number, balance, is_registered, is_blocked) " +
            "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String ADD_SERVICE =
            "INSERT INTO users_services (user_id, service_id) VALUES (?, ?)";
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
    private final String GET_SERVICES =
            "SELECT * FROM users " +
            "INNER JOIN users_services ON users.id = user_id " +
            "INNER JOIN services ON service_id = services.id WHERE users.id = ?";
    private final String GET_INVOICES =
            "SELECT * FROM users " +
            "INNER JOIN invoices ON users.id = user_id WHERE users.id = ?";
    private final String GET_USER_BALANCE =
            "SELECT balance FROM users WHERE id = ? FOR UPDATE";
    private final String UPDATE_USER_BALANCE =
            "UPDATE users SET balance = balance + ? WHERE id = ?";


    private UserDaoImpl() {
    }

    private static class SingletonHolder {
        private static final UserDaoImpl INSTANCE = new UserDaoImpl();
    }

    public static UserDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addUser(Connection connection, User user) {
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
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public long addService(long userId, long serviceId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            // 1) switch on the service for the user
            preparedStatement = connection.prepareStatement(ADD_SERVICE);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, serviceId);
            preparedStatement.executeUpdate();
            // 2) get the switched on service
            ServiceDao serviceDao = ServiceDaoImpl.getInstance();
            Service service = serviceDao.getService(connection, serviceId);
            // 3) create invoice for the current service
            Invoice invoice = new Invoice();
            invoice.setDate(LocalDate.now());
            invoice.setDescription("Invoice for a service " + service.getName());
            invoice.setPrice(service.getPrice());
            invoice.setPaid(false);
            // 4) add the corresponding invoice into the data base
            InvoiceDao invoiceDao = InvoiceDaoImpl.getInstance();
            invoiceDao.addInvoice(connection, invoice, userId);

//            // 3) get the user balance
//            preparedStatement = connection.prepareStatement(GET_USER_BALANCE);
//            preparedStatement.setLong(1, userId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                currentBalance = resultSet.getInt("balance");
//            }
//            // 4) withdraw money for the service
//            preparedStatement = connection.prepareStatement(UPDATE_USER_BALANCE);
//            preparedStatement.setInt(1, currentBalance - price);
//            preparedStatement.setLong(2, userId);
//            preparedStatement.executeUpdate();

            // 4) commit
            connection.commit();
            return serviceId;
        } catch (SQLException e) {
            DBManager.rollback(connection);
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
            DBManager.closeStatement(preparedStatement);
        }
        return -1;
    }

    @Override
    public User getUserById(long id) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_PHONE_NUMBER);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_UNREGISTERED_USERS);
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
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DEBTORS);
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
    public List<Service> getServices(long id) {
        List<Service> services = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICES);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Service service = createService(resultSet);
                services.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public List<Invoice> getInvoices(long id) {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INVOICES);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Invoice invoice = createInvoice(resultSet);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public boolean updateBalance(long userId, int sum) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BALANCE);
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

    private Service createService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("services.id"));
        service.setName(resultSet.getString("services.name"));
        service.setDescription(resultSet.getString("services.description"));
        service.setPrice(resultSet.getInt("services.price"));
        return service;
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong("invoices.id"));
        invoice.setDate(resultSet.getDate("invoices.date").toLocalDate());
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
