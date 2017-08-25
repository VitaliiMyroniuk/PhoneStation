package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserService {
    void addUser(User user);
    User getUser(Long id);
    User getUser(String login);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(Long id);

    /**
     * The method checks the given login and password.
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with user; <br>
     *         -1 if there is no such login and password in the data base.
     */
    byte checkLogin(String login, String password);
}
