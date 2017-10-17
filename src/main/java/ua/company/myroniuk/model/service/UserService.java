package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.LoginExistsException;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.exception.PhoneNumberExistsException;
import java.util.List;

/**
 * The interface describes the behavior of the {@code UserService} object.
 *
 * @author Vitalii Myroniuk
 */
public interface UserService {
    /**
     * The method inserts the given {@code User} object into the data base.
     * Corresponding {@code Account} object is inserted into the data base as well.
     * Inserted user has no active services and no unpaid invoices.
     *
     *
     * @param user the given {@code User} object.
     * @return id of the {@code User} object inserted into the data base.
     * @throws LoginExistsException if the user with the given login already exists.
     * @throws PhoneNumberExistsException if the user with the given phone number already exists.
     */
    long addUser(User user) throws LoginExistsException, PhoneNumberExistsException;

    /**
     * The method retrieves the {@code User} object with his account from the data base.
     * The list of active services and list of his unpaid invoices is not retrieved.
     *
     * @param id id of the user to be retrieved.
     * @return {@code User} object retrieved from the data base.
     */
    User getUserById(long id);

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
     * The method retrieves the list of all registered users from the data base.
     * For every user a corresponding [@code Account} object is retrieved as well
     * but the list of his active services and list of his unpaid invoices are not retrieved.
     *
     * @return the list of all registered users retrieved from the data base.
     */
    List<User> getRegisteredUsers();

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
     * The method computes the total debt of the given user.
     *
     * @param user {@code User} object.
     * @return the total debt of the given user.
     */
    int getDebt(User user);

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
     * The method deletes the {@code User} object and his account from the data base.
     * A list of his active services and a list of his invoices are deleted as well.
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

    /**
     * The method deletes the given invoice of the user
     * from the corresponding list of the unpaid invoices.
     * The paid status of the given invoice is change to true.
     *
     * @param userId id of the user that has to pay the invoice.
     * @param invoiceId id of the invoice to be paid.
     * @return true if the given invoice of the user is paid successfully.
     * @throws NotEnoughMoneyException if there is not enough money to pay invoice.
     */
    boolean payInvoice(long userId, long invoiceId) throws NotEnoughMoneyException;

    /**
     * The method activates the given service for the user with specified id.
     *
     * @param userId id of the user for whom the service should be activated.
     * @param serviceId id of the service to be activated.
     * @return true if the given service is activated successfully.
     */
    long switchOnService(long userId, long serviceId);

    /**
     * The method inactivates the given service for the user with specified id.
     *
     * @param userId id of the user for whom the service should be inactivated.
     * @param serviceId id of the service to be inactivated.
     * @return true if the given service is inactivated successfully.
     */
    boolean switchOffService(long userId, long serviceId);
}
