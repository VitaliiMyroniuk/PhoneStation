package ua.company.myroniuk.controller.command.general;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.company.myroniuk.model.exception.LoginExistsException;
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
    public void checkSuccessfulInputDuringRegistration() {
        when(request.getParameter("name")).thenReturn("Anna-Maria");
        when(request.getParameter("middle_name")).thenReturn("В'ячеславівна");
        when(request.getParameter("surname")).thenReturn("ді Вітторіо");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("anna_maria");
        when(request.getParameter("password")).thenReturn("!@#$%^&*");
        when(request.getParameter("confirmed_password")).thenReturn("!@#$%^&*");
        String path = registrationCommand.execute(request, response);
        assertEquals(path, REDIRECT_TO_SUCCESSFUL_REGISTRATION_JSP);
    }

    @Test
    public void checkIncorrectNameInputDuringRegistration() {
        when(request.getParameter("name")).thenReturn("<some script>");
        when(request.getParameter("middle_name")).thenReturn("Семенович");
        when(request.getParameter("surname")).thenReturn("Семенов");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("!@#$%^&*");
        when(request.getParameter("confirmed_password")).thenReturn("!@#$%^&*");
        String path = registrationCommand.execute(request, response);
        assertEquals(path, REGISTRATION_JSP);
    }

    @Test
    public void checkIncorrectPhoneNumberInputDuringRegistration() {
        when(request.getParameter("name")).thenReturn("Іван");
        when(request.getParameter("middle_name")).thenReturn("Іванович");
        when(request.getParameter("surname")).thenReturn("Іванов");
        when(request.getParameter("phone_number")).thenReturn("+3804444");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("!@#$%^&*");
        when(request.getParameter("confirmed_password")).thenReturn("!@#$%^&*");
        String path = registrationCommand.execute(request, response);
        assertEquals(path, REGISTRATION_JSP);
    }

    @Test
    public void checkUnequalPasswordsInputDuringRegistration() {
        when(request.getParameter("name")).thenReturn("Іван");
        when(request.getParameter("middle_name")).thenReturn("Іванович");
        when(request.getParameter("surname")).thenReturn("Іванов");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmed_password")).thenReturn("!@#$%^&*");
        String path = registrationCommand.execute(request, response);
        assertEquals(path, REGISTRATION_JSP);
    }

    @Test
    public void checkExistingLoginInputDuringRegistration() throws Exception {
        when(request.getParameter("name")).thenReturn("Петро");
        when(request.getParameter("middle_name")).thenReturn("Петрович");
        when(request.getParameter("surname")).thenReturn("Петров");
        when(request.getParameter("phone_number")).thenReturn("+380441111111");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("confirmed_password")).thenReturn("password");
        doThrow(new LoginExistsException()).when(userService).addUser(anyObject());
        String path = registrationCommand.execute(request, response);
        assertEquals(path, REGISTRATION_JSP);
    }
}