package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {

    long addUser(Connection connection, User user);

    long addService(long userId, long serviceId);

    User getUserById(long id);

    User getUserByLogin(String login);

    User getUserByPhoneNumber(String phoneNumber);

    List<User> getAllUsers();

    List<User> getUnregisteredUsers();
    
    List<User> getDebtors();

    List<Service> getServices(long id);

    List<Invoice> getInvoices(long id);

    boolean updateBalance(long userId, int sum);
}
