package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.User;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface UserDao {

    /**
     *
     * @param user
     * @return
     */
    Long addUser(User user);

    /**
     *
     * @param login
     * @return
     */
    User getUser(String login);

    /**
     *
     * @return
     */
    List<User> getUnregisteredUsers();

    /**
     *
     * @return
     */
    List<User> getDebtors();
}
