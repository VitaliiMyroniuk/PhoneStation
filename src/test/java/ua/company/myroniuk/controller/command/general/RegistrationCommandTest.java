package ua.company.myroniuk.controller.command.general;

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

/**
 * @author Vitalii Myroniuk
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegistrationCommand registrationCommand;

    @Test
    public void execute() throws Exception {
        when(request.getParameter("name")).thenReturn("Ivan");
        when(request.getParameter("middle_name")).thenReturn("Ivanovych");
        when(request.getParameter("surname")).thenReturn("Ivanov");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("ivanov");
        when(request.getParameter("password")).thenReturn("ivanov");
        when(request.getParameter("confirmed_password")).thenReturn("ivanov");
        when(request.getParameter("name")).thenReturn("Ivan");
        String page = registrationCommand.execute(request, response);
        verify(request, times(14)).getParameter(anyString());
        assertEquals(page, "redirect:/phone_station/successful_registration");
    }
}