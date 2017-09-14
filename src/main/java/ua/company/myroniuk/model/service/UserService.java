package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserService {

    long addUser(User user);

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

    boolean checkPhoneNumber(String phoneNumber);
}
