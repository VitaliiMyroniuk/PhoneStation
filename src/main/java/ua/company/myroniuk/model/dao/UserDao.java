package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {

    long addUser(Connection connection, User user) throws SQLException;

    User getUserById(long id);

    User getUserWithInvoicesById(long id);

    User getUserByLogin(String login);

    User getUserByPhoneNumber(String phoneNumber);

    List<User> getRegisteredUsers();

    List<User> getUnregisteredUsers();
    
    List<User> getDebtors();

    int[] getUserCountInfo();

    boolean updateBalance(long userId, int sum);

    boolean updateBalance(Connection connection, long userId, int sum) throws SQLException;

    boolean updateIsRegistered(long userId);

    boolean updateIsBlocked(long userId, boolean isBlocked);

    boolean updateIsBlocked(Connection connection, long userId, boolean isBlocked) throws SQLException;

    boolean deleteUser(Connection connection, long id);
}