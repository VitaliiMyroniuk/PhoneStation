package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.AccountDao;
import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.service.AccountService;

/**
 * The class represents the service for the {@code Account} object.
 * It implements {@code AccountService} interface.
 *
 * @author Vitalii Myroniuk
 */
public class AccountServiceImpl implements AccountService {
    /**
     * DaoFactory object.
     */
    private DaoFactory daoFactory;

    /**
     * Constructor for creating empty {@code AccountServiceImpl} object.
     */
    private AccountServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    /**
     * The class {@code SingletonHolder} is the auxiliary static nested class
     * for the thread safe (Bill Pugh) singleton implementation.
     */
    private static class SingletonHolder {
        private static final AccountServiceImpl INSTANCE = new AccountServiceImpl();
    }

    /**
     * The methods provides creating or getting already created {@code AccountServiceImpl} object.
     *
     * @return {@code AccountServiceImpl} object.
     */
    public static AccountServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * The method checks the given login and password.
     *
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with registered user; <br>
     *         -1 if there is no registered user with such login and password.
     */
    @Override
    public int checkLoginAndPassword(String login, String password) {
        AccountDao accountDao = daoFactory.createAccountDao();
        return accountDao.checkLoginAndPassword(login, password);
    }

    /**
     * The method checks if the given login is in the data base.
     *
     * @param login the login entered by the user.
     * @return true if the given login is in the data base; <br>
     *         false otherwise.
     */
    @Override
    public boolean checkLogin(String login) {
        AccountDao accountDao = daoFactory.createAccountDao();
        Account account = accountDao.getAccountByLogin(login);
        return account != null;
    }
}
