package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Account;

/**
 * The interface describes the behavior of the {@code AccountDao} object.
 *
 * @author Vitalii Myroniuk
 */
public interface AccountDao {
    /**
     * The method inserts the given {@code Account} object into the data base.
     *
     * @param account the given {@code Account} object.
     * @return id of the {@code Account} object inserted into the data base.
     */
    long addAccount(Account account);

    /**
     * The method retrieves an {@code Account} object with the given login from the data base.
     *
     * @param login the {@code login} of the {@code Account} object.
     * @return {@code Account} object retrieved from the data base.
     */
    Account getAccountByLogin(String login);

    /**
     * The method deletes the {@code Account} object with the given id from the data base.
     *
     * @param accountId id of the {@code Account} object that should be deleted.
     * @return true if the {@code Account} object is deleted.
     */
    boolean deleteAccount(long accountId);
}
