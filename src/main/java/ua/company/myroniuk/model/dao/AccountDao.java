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
     * The method retrieves an {@code Account} object from the data base.
     *
     * @param login the login of the {@code Account} object to be retrieved from the data base.
     * @return {@code Account} object retrieved from the data base.
     */
    Account getAccountByLogin(String login);

    /**
     * The method deletes the {@code Account} object from the data base.
     *
     * @param id id of the {@code Account} object to be deleted.
     * @return true if the {@code Account} object is deleted.
     */
    boolean deleteAccount(long id);
}
