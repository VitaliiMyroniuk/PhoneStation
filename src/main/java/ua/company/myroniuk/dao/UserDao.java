package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {
    void addUser(User user);
    User getUser(Long id);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);
}
