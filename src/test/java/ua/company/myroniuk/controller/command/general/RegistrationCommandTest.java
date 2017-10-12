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
import static ua.company.myroniuk.controller.command.Command.REGISTRATION_JSP;

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
    public void checkSuccessfulRegistration() {
        when(request.getParameter("name")).thenReturn("Anna-Maria");
        when(request.getParameter("middle_name")).thenReturn("В'ячеславівна");
        when(request.getParameter("surname")).thenReturn("ді Вітторіо");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("anna_maria");
        when(request.getParameter("password")).thenReturn("!@#$%^&*");
        when(request.getParameter("confirmed_password")).thenReturn("!@#$%^&*");
        String path = registrationCommand.execute(request, response);
        verify(request, times(14)).getParameter(anyString());
        assertEquals(path, "redirect:/phone_station/successful_registration");
    }

    @Test
    public void checkIncorrectInputDuringRegistration() {
        when(request.getParameter("name")).thenReturn("<some script>");
        String path = registrationCommand.execute(request, response);
        verify(request, times(8)).getParameter(anyString());
        assertEquals(path, REGISTRATION_JSP);
    }
}