package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserService {

    long addUser(User user);

    User getUser(String login);

    List<User> getAllUsers();

    List<User> getUnregisteredUsers();

    List<User> getDebtors();

    List<Service> getServices(long id);

    boolean checkPhoneNumber(String phoneNumber);
}
