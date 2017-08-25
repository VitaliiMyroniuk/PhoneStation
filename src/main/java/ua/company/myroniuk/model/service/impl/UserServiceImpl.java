package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.UserDao;
import ua.company.myroniuk.dao.impl.UserDaoImpl;
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
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUser(String login) {
        return userDao.getUser(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
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
    public byte checkLogin(String login, String password) {
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
}
