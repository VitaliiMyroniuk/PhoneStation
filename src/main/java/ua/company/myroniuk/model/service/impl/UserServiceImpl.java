package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.*;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.LoginExistsException;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.exception.PhoneNumberExistsException;
import ua.company.myroniuk.model.service.UserService;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The class represents the service for the {@code User} object.
 * It implements {@code UserService} interface.
 *
 * @author Vitalii Myroniuk
 */
public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory;

    private UserServiceImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * The class {@code SingletonHolder} is the auxiliary static nested class
     * for the thread safe (Bill Pugh) singleton implementation.
     */
    private static class SingletonHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl(DaoFactory.getInstance());
    }

    /**
     * The methods provides creating or getting already created {@code UserServiceImpl} object.
     *
     * @return {@code UserServiceImpl} object.
     */
    public static UserServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addUser(User user) throws LoginExistsException, PhoneNumberExistsException {
        long userId;
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            AccountDao accountDao = daoFactory.createAccountDao(daoConnection);
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            daoConnection.beginTransaction();
            Account account = user.getAccount();
            String login = account.getLogin();
            if (accountDao.getAccountByLogin(login) != null) {
                throw new LoginExistsException();
            }
            String phoneNumber = user.getPhoneNumber();
            if (userDao.getUserByPhoneNumber(phoneNumber) != null) {
                throw new PhoneNumberExistsException();
            }
            long accountId = accountDao.addAccount(account);
            account.setId(accountId);
            user.setAccount(account);
            userId = userDao.addUser(user);
            daoConnection.commitTransaction();
        }
        return userId;
    }

    @Override
    public User getUserById(long id) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getUserById(id);
        }
    }

    @Override
    public User getUserWithUnpaidInvoicesById(long id) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getUserWithUnpaidInvoicesById(id);
        }
    }

    @Override
    public List<User> getRegisteredUsers() {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getRegisteredUsers();
        }
    }

    @Override
    public List<User> getUnregisteredUsers() {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getUnregisteredUsers();
        }
    }

    @Override
    public List<User> getDebtors() {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getDebtors();
        }
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
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.getUserCountInfo();
        }

    }

    @Override
    public boolean updateBalance(long userId, int sum) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.updateBalance(userId, sum);
        }
    }

    @Override
    public boolean updateIsRegistered(long userId) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.updateIsRegistered(userId);
        }
    }

    @Override
    public boolean updateIsBlocked(long userId, boolean isBlocked) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.updateIsBlocked(userId, isBlocked);
        }
    }

    @Override
    public boolean deleteUser(long userId) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            AccountDao accountDao = daoFactory.createAccountDao(daoConnection);
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            ServiceDao serviceDao = daoFactory.createServiceDao(daoConnection);
            InvoiceDao invoiceDao = daoFactory.createInvoiceDao(daoConnection);
            daoConnection.beginTransaction();
            User user = userDao.getUserById(userId);
            long accountId = user.getAccount().getId();
            invoiceDao.deleteInvoices(userId);
            serviceDao.deleteUserServices(userId);
            userDao.deleteUser(userId);
            accountDao.deleteAccount(accountId);
            daoConnection.commitTransaction();
        }
        return true;
    }

    @Override
    public User logIn(String login, String password) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            return userDao.logIn(login, password);
        }
    }

    @Override
    public boolean payInvoice(long userId, long invoiceId) throws NotEnoughMoneyException {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            UserDao userDao = daoFactory.createUserDao(daoConnection);
            InvoiceDao invoiceDao = daoFactory.createInvoiceDao(daoConnection);
            daoConnection.beginTransaction();
            User user = userDao.getUserWithUnpaidInvoicesById(userId);
            Invoice invoice = invoiceDao.getInvoice(invoiceId);
            if (user.getBalance() < invoice.getPrice()) {
                throw new NotEnoughMoneyException();
            }
            userDao.updateBalance(userId, - invoice.getPrice());
            invoiceDao.updateIsPaid(true, invoiceId);
            if (user.getInvoices().size() == 1) {
                userDao.updateIsBlocked(userId, false);
            }
            daoConnection.commitTransaction();
        }
        return true;
    }

    @Override
    public long switchOnService(long userId, long serviceId) {
        long usersServicesId;
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            InvoiceDao invoiceDao = daoFactory.createInvoiceDao(daoConnection);
            ServiceDao serviceDao = daoFactory.createServiceDao(daoConnection);
            daoConnection.beginTransaction();
            Service service = serviceDao.getService(serviceId);
            usersServicesId = serviceDao.addUserService(service, userId);
            Invoice invoice = createInvoice(service);
            invoiceDao.addInvoice(invoice, userId);
            daoConnection.commitTransaction();
        }
        return usersServicesId;
    }

    @Override
    public boolean switchOffService(long userId, long serviceId) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            ServiceDao serviceDao = daoFactory.createServiceDao(daoConnection);
            return serviceDao.deleteUserService(userId, serviceId);
        }
    }

    private Invoice createInvoice(Service service) {
        return new Invoice.Builder()
                .setDateTime(LocalDateTime.now())
                .setDescription("Invoice for a service " + service.getName())
                .setPrice(service.getPrice())
                .setPaid(false)
                .build();
    }
}
