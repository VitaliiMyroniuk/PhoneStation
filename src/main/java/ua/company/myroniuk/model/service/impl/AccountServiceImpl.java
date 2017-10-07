package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.service.AccountService;

/**
 * The class represents the service for the {@code Account} object.
 * It implements {@code AccountService} interface.
 *
 * @author Vitalii Myroniuk
 */
public class AccountServiceImpl implements AccountService {
    private DaoFactory daoFactory;

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
}
