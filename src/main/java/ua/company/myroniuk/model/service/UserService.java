package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.LoginExistsException;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.exception.PhoneNumberExistsException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserService {

    long addUser(User user) throws LoginExistsException, PhoneNumberExistsException;

    User getUserById(long id);

    User getUserWithUnpaidInvoicesById(long id);

    User getUserByLogin(String login);

    List<User> getRegisteredUsers();

    List<User> getUnregisteredUsers();

    List<User> getDebtors();

    int getDebt(User user);

    int[] getUserCountInfo();

    boolean updateIsRegistered(long userId);

    boolean updateIsBlocked(long userId, boolean isBlocked);

    boolean updateBalance(long userId, int sum);

    boolean deleteUser(long userId);

    User logIn(String login, String password);

    boolean payInvoice(long userId, long invoiceId) throws NotEnoughMoneyException;

    long switchOnService(long userId, long serviceId);

    boolean switchOffService(long userId, long serviceId);
}
