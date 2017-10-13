package ua.company.myroniuk.controller.command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.company.myroniuk.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ua.company.myroniuk.controller.command.Command.*;

/**
 * @author Vitalii Myroniuk
 */
@RunWith(MockitoJUnitRunner.class)
public class BlockUserCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private BlockUserCommand blockUserCommand;

    @Test
    public void checkSuccessfulUserBlocking() {
        when(request.getParameter("user_id")).thenReturn("1");
        String path = blockUserCommand.execute(request, response);
        verify(request).getParameter("user_id");
        verify(userService).updateIsBlocked(1, true);
        assertEquals(path, REDIRECT_TO_USER_INVOICES_JSP + 1);
    }
}