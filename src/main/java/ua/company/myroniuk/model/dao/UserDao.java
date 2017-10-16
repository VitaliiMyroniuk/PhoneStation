package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * The interface describes the behavior of the {@code UserDao} object.
 *
 * @author Vitalii Myroniuk
 */
public interface UserDao {
    /**
     * The method inserts the given {@code User} object into the data base.
     *
     * @param user the {@code User} object to be inserted into the data base.
     * @return id of the {@code User} object inserted into the data base.
     */
    long addUser(User user);

    /**
     * The method retrieves the {@code User} object with his account from the data base.
     * The list of his active services and list of his unpaid invoices are not retrieved.
     *
     * @param id id of the user to be retrieved.
     * @return {@code User} object retrieved from the data base.
     */
    User getUserById(long id);

    /**
     * The method retrieves the {@code User} object with his account from the data base.
     * The list of his active services and list of his unpaid invoices are not retrieved.
     *
     * @param login the login of the user to be retrieved from the data base.
     * @return {@code User} object retrieved from the data base.
     */
    User getUserByLogin(String login);

    /**
     * The method retrieves the {@code User} object with his account from the data base.
     * The list of his active services and list of his unpaid invoices are not retrieved.
     *
     * @param phoneNumber the phone number of the user to be retrieved from the data base.
     * @return {@code User} object retrieved from the data base.
     */
    User getUserByPhoneNumber(String phoneNumber);

    /**
     * The method retrieves the {@code User} object with his account from the data base.
     * A list of unpaid invoices of this user is retrieved as well.
     * A list of active services of this user is not retrieved.
     *
     * @param id id of the user to be retrieved.
     * @return {@code User} object retrieved from the data base.
     */
    User getUserWithUnpaidInvoicesById(long id);

    /**
     * The method retrieves the list of registered users from the data base.
     * For every user a corresponding [@code Account} object is retrieved as well
     * but the list of his active services and list of his unpaid invoices are not retrieved.
     *
     * @param from position from which we start to retrieve the users from the data base.
     * @param count number of users that we are going to retrieve from the data base.
     * @return the list of all registered users retrieved from the data base.
     */
    List<User> getRegisteredUsers(int from, int count);

    /**
     * The method retrieves the list of all unregistered users from the data base.
     * For every user a corresponding [@code Account} object is retrieved as well.
     * Every such user has no active services and no unpaid invoices.
     *
     * @return the list of all unregistered users retrieved from the data base.
     */
    List<User> getUnregisteredUsers();

    /**
     * The method retrieves the list of all registered users from the data base
     * that have unpaid invoices, i.e. the list of debtors.
     * For every user a corresponding [@code Account} object is retrieved as well
     * but the list of his active services and list of his invoices are not retrieved.
     *
     * @return the list of all debtors retrieved from the data base.
     */
    List<User> getDebtors();

    /**
     * The method retrieves the user count information about
     * all registered users, all unregistered users and all debtors.
     *
     * @return {@code int[] array} where  <br>
     *         array[0] - count of all registered users; <br>
     *         array[1] - count of all unregistered users; <br>
     *         array[2] - count of all debtors. <br>
     */
    int[] getUserCountInfo();

    /**
     * The method updates the user balance.
     *
     * @param id id of the user whose balance has to be updated.
     * @param sum the sum by which the user balance is updated.
     * @return true if the user balance is updated successfully.
     */
    boolean updateBalance(long id, int sum);

    /**
     * The method updates the user registration status for true.
     *
     * @param id id of the user whose registration status has to be updated.
     * @return true if the user registration status is updated successfully.
     */
    boolean updateIsRegistered(long id);

    /**
     * The method updates the user blocked status.
     *
     * @param id id of the user whose block status has to be updated.
     * @param isBlocked true if the user is blocked and false otherwise.
     * @return true if the user block status is updated successfully.
     */
    boolean updateIsBlocked(long id, boolean isBlocked);

    /**
     * The method deletes the {@code User} object from the data base.
     *
     * @param id id of the user to be deleted.
     * @return true if the {@code User} object is deleted.
     */
    boolean deleteUser(long id);

    /**
     * The method tries to retrieve the {@code User} object with his account from the data base.
     * The retrieved user has to have the login and password that coincide
     * with the specified login and password.
     *
     * @param login the login of the user to be retrieved from the data base.
     * @param password the password of the user to be retrieved from the data base.
     * @return {@code User} object retrieved from the data base
     *         or null if there is no user with the specified login and password.
     */
    User logIn(String login, String password);
}
