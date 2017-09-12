package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.UserDao;
import ua.company.myroniuk.dao.impl.UserDaoImpl;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.Service;
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
    public long addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<User> getUnregisteredUsers() {
        return userDao.getUnregisteredUsers();
    }

    @Override
    public List<User> getDebtors() {
        return userDao.getDebtors();
    }

    @Override
    public List<Service> getServices(long id) {
        return userDao.getServices(id);
    }

    @Override
    public List<Invoice> getInvoices(long id) {
        return userDao.getInvoices(id);
    }

    @Override
    public boolean updateBalance(long userId, int sum) {
        return userDao.updateBalance(userId, sum);
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        return userDao.getUserByPhoneNumber(phoneNumber) != null;
    }
}
