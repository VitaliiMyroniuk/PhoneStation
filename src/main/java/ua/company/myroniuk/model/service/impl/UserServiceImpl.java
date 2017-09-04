package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.UserDao;
import ua.company.myroniuk.dao.impl.UserDaoImpl;
import ua.company.myroniuk.model.entity.SimCard;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    private static class SingletonHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    /**
     * The method checks the given login and password.
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with user; <br>
     *         -1 if there is no such login and password in the data base.
     */
    @Override
    public byte checkLoginAndPassword(String login, String password) {
        User user = userDao.getUser(login);
        if (user != null && user.getPassword().equals(password)) {
            if (user.isAdmin()) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

    /**
     * The method checks if the given login is in the data base.
     * @param login the login entered by the user.
     * @return true if the given login is in the data base; <br>
     *         false otherwise.
     */
    @Override
    public boolean checkLogin(String login) {
        User user = userDao.getUser(login);
        return user != null;
    }
}
