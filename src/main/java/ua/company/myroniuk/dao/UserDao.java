package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {

    long addUser(User user);

    User getUser(String login);

    List<User> getAllUsers();

    List<User> getUnregisteredUsers();
    
    List<User> getDebtors();

    List<Service> getServices(long id);

    boolean checkPhoneNumber(String phoneNumber);
}
