package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.AccountDao;
import ua.company.myroniuk.dao.impl.AccountDaoImpl;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.service.AccountService;

/**
 * @author Vitalii Myroniuk
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = AccountDaoImpl.getInstance();

    private AccountServiceImpl() {
    }

    private static class SingletonHolder {
        private static final AccountServiceImpl INSTANCE = new AccountServiceImpl();
    }

    public static AccountServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * The method checks the given login and password.
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with registered user; <br>
     *         -1 if there is no registered user with such login and password.
     */
    @Override
    public int checkLoginAndPassword(String login, String password) {
        return accountDao.checkLoginAndPassword(login, password);
    }

    /**
     * The method checks if the given login is in the data base.
     * @param login the login entered by the user.
     * @return true if the given login is in the data base; <br>
     *         false otherwise.
     */
    @Override
    public boolean checkLogin(String login) {
        Account account = accountDao.getAccount(login);
        return account != null;
    }
}
