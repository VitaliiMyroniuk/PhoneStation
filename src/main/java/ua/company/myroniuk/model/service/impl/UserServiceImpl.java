package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.*;
import ua.company.myroniuk.dao.impl.AccountDaoImpl;
import ua.company.myroniuk.dao.impl.InvoiceDaoImpl;
import ua.company.myroniuk.dao.impl.ServiceDaoImpl;
import ua.company.myroniuk.dao.impl.UserDaoImpl;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserServiceImpl implements UserService {

    private AccountDao accountDao = AccountDaoImpl.getInstance();
    private UserDao userDao = UserDaoImpl.getInstance();
    private ServiceDao serviceDao = ServiceDaoImpl.getInstance();
    private InvoiceDao invoiceDao = InvoiceDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    private static class SingletonHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addUser(User user) {
        long userId = -1;
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            Account account = user.getAccount();
            long accountId = accountDao.addAccount(connection, account);
            account.setId(accountId);
            user.setAccount(account);
            userId = userDao.addUser(connection, user);
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return userId;
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getUnregisteredUsers() {
        return userDao.getUnregisteredUsers();
    }

    @Override
    public List<User> getDebtors() {
        return userDao.getDebtors();
    }

    @Override
    public int[] getUserCountInfo() {
        return userDao.getUserCountInfo();
    }

    @Override
    public boolean updateBalance(long userId, int sum) {
        return userDao.updateBalance(userId, sum);
    }

    @Override
    public boolean updateIsRegistered(long userId) {
        return userDao.updateIsRegistered(userId);
    }

    @Override
    public boolean updateIsBlocked(long userId, boolean isBlocked) {
        return userDao.updateIsBlocked(userId, isBlocked);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public boolean payInvoice(long userId, long invoiceId) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            // 1) get the invoice that has to be paid
            Invoice invoice = invoiceDao.getInvoice(connection, invoiceId);
            // 2) update user balance (withdraw money)
            userDao.updateBalance(connection, userId, - invoice.getPrice());
            // 3) update corresponding invoice from the data base
            invoiceDao.updateIsPaid(connection, true, invoiceId);
            // 4) commit
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return false;
    }

    @Override
    public long switchOnService(long userId, long serviceId) {
        long usersServicesId = -1;
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            // 1) get the service that has to be switched
            Service service = serviceDao.getService(connection, serviceId);
            // 2) add the record into users_services table
            serviceDao.addUserService(connection, service, userId);
            // 3) create invoice for the current service
            Invoice invoice = new Invoice();
            invoice.setDateTime(LocalDateTime.now());
            invoice.setDescription("Invoice for a service " + service.getName());
            invoice.setPrice(service.getPrice());
            invoice.setPaid(false);
            // 4) add the corresponding invoice into the data base
            invoiceDao.addInvoice(connection, invoice, userId);
            // 5) commit
            connection.commit();
        } catch (SQLException e) {
            DBManager.rollback(connection);
            e.printStackTrace();
        } finally {
            DBManager.closeConnection(connection);
        }
        return usersServicesId;
    }

    @Override
    public boolean switchOffService(long userId, long serviceId) {
        return serviceDao.deleteUserService(userId, serviceId);
    }
}
