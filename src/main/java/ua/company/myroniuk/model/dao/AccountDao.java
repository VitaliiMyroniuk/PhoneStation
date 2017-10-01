package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Account;

/**
 * @author Vitalii Myroniuk
 */
public interface AccountDao {

    long addAccount(Account account);

    Account getAccountByLogin(String login);

    boolean deleteAccount(long accountId);
}
