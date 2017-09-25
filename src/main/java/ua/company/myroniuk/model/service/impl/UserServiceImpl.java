package ua.company.myroniuk.model.service.impl;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.*;
import ua.company.myroniuk.model.dao.impl.AccountDaoImpl;
import ua.company.myroniuk.model.dao.impl.InvoiceDaoImpl;
import ua.company.myroniuk.model.dao.impl.ServiceDaoImpl;
import ua.company.myroniuk.model.dao.impl.UserDaoImpl;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.service.UserService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

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
            LOGGER.error("Error during adding the user into the data base: ", e);
            DBManager.rollback(connection);
            throw new RuntimeException(e);
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
    public User getUserWithInvoicesById(long id) {
        return userDao.getUserWithInvoicesById(id);
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
    public List<User> getRegisteredUsers() {
        return userDao.getRegisteredUsers();
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
    public int getDebt(User user) {
        int debt = 0;
        for (Invoice invoice : user.getInvoices()) {
            debt += invoice.getPrice();
        }
        return debt;
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
    public boolean deleteUser(long userId) {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            User user = userDao.getUserById(userId);
            long accountId = user.getAccount().getId();
            invoiceDao.deleteInvoices(connection, userId);
            serviceDao.deleteUserServices(connection, userId);
            userDao.deleteUser(connection, userId);
            accountDao.deleteAccount(connection, accountId);
            connection.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the user from the data base: ", e);
            DBManager.rollback(connection);
            throw new RuntimeException(e);
        } finally {
            DBManager.closeConnection(connection);
        }
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public boolean payInvoice(long userId, long invoiceId) throws NotEnoughMoneyException {
        Connection connection = null;
        try {
            connection = DBManager.getConnection();
            connection.setAutoCommit(false);
            User user = userDao.getUserById(userId);
            Invoice invoice = invoiceDao.getInvoice(connection, invoiceId);
            if (user.getBalance() < invoice.getPrice()) {
                throw new NotEnoughMoneyException();
            }
            userDao.updateBalance(connection, userId, - invoice.getPrice());
            invoiceDao.updateIsPaid(connection, true, invoiceId);
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error during invoice paying: ", e);
            DBManager.rollback(connection);
            throw new RuntimeException(e);
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
            Service service = serviceDao.getService(connection, serviceId);
            serviceDao.addUserService(connection, service, userId);
            Invoice invoice = new Invoice();
            invoice.setDateTime(LocalDateTime.now());
            invoice.setDescription("Invoice for a service " + service.getName());
            invoice.setPrice(service.getPrice());
            invoice.setPaid(false);
            invoiceDao.addInvoice(connection, invoice, userId);
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error during switching on the service: ", e);
            DBManager.rollback(connection);
            throw new RuntimeException(e);
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
