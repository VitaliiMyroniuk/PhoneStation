package ua.company.myroniuk.model.service.impl;

import org.junit.Before;
import org.junit.Test;
import ua.company.myroniuk.model.dao.DaoConnection;
import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.dao.UserDao;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Vitalii Myroniuk
 */
public class UserServiceImplTest {
    private DaoFactory daoFactory;
    private DaoConnection daoConnection;
    private UserDao userDao;
    private UserService userService;

    @Before
    public void init() {
        daoFactory = mock(DaoFactory.class);
        daoConnection = mock(DaoConnection.class);
        userDao = mock(UserDao.class);
        userService = new UserServiceImpl(daoFactory);
    }

    @Test
    public void getUserByLogin() throws Exception {
        User user = new User();
        when(daoFactory.getDaoConnection()).thenReturn(daoConnection);
        when(daoFactory.createUserDao(anyObject())).thenReturn(userDao);
        when(userDao.getUserByLogin("someLogin")).thenReturn(user);
        User resultUser = userService.getUserByLogin("someLogin");
        verify(daoFactory).getDaoConnection();
        verify(daoFactory).createUserDao(daoConnection);
        assertEquals(user, resultUser);
    }
}