package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {

    long addUser(User user);

    User getUserById(long id);

    boolean deleteUser(long id);

    User getUserWithUnpaidInvoicesById(long id);

    User getUserByLogin(String login);

    User getUserByPhoneNumber(String phoneNumber);

    List<User> getRegisteredUsers();

    List<User> getUnregisteredUsers();
    
    List<User> getDebtors();

    int[] getUserCountInfo();

    boolean updateBalance(long userId, int sum);

    boolean updateIsRegistered(long userId);

    boolean updateIsBlocked(long userId, boolean isBlocked);

    User logIn(String login, String password);
}
