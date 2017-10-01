package ua.company.myroniuk.controller.command.admin;

import org.junit.Before;
import org.junit.Test;
import ua.company.myroniuk.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Vitalii Myroniuk
 */
public class BlockUserCommandTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private UserService userService;
    private BlockUserCommand blockUserCommand;

    @Before
    public void init() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        userService = mock(UserService.class);
        blockUserCommand = new BlockUserCommand(userService);
    }

    @Test
    public void execute() throws Exception {
        when(request.getParameter("user_id")).thenReturn("1");
        String path = blockUserCommand.execute(request, response);
        verify(request).getParameter("user_id");
        verify(userService).updateIsBlocked(1, true);
        assertEquals(path, "redirect:/phone_station/user_invoices?user_id=1");
    }
}