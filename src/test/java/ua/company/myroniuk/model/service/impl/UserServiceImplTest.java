package ua.company.myroniuk.model.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.company.myroniuk.model.dao.DaoConnection;
import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.dao.UserDao;
import ua.company.myroniuk.model.entity.User;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Vitalii Myroniuk
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private DaoFactory daoFactory;

    @Mock
    private DaoConnection daoConnection;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

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