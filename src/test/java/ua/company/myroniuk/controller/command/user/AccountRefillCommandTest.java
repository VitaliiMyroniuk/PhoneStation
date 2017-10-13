package ua.company.myroniuk.controller.command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static ua.company.myroniuk.controller.command.Command.*;

/**
 * @author Vitalii Myroniuk
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountRefillCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private UserService userService;

    @InjectMocks
    private AccountRefillCommand accountRefillCommand;

    @Test
    public void checkSuccessfulInputDuringAccountRefill() {
        when(request.getParameter("credit_card_number")).thenReturn("1234567891234567");
        when(request.getParameter("cvv")).thenReturn("123");
        when(request.getParameter("sum")).thenReturn("0.25");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(new User());
        String path = accountRefillCommand.execute(request, response);
        assertEquals(path, REDIRECT_TO_USER_PROFILE_JSP);
    }

    @Test
    public void checkIncorrectCreditCardNumberInputDuringAccountRefill() {
        when(request.getParameter("credit_card_number")).thenReturn("1234");
        when(request.getParameter("cvv")).thenReturn("123");
        when(request.getParameter("sum")).thenReturn("0.25");
        String path = accountRefillCommand.execute(request, response);
        assertEquals(path, ACCOUNT_REFILL_JSP);
    }

    @Test
    public void checkIncorrectSumInputDuringAccountRefill() {
        when(request.getParameter("credit_card_number")).thenReturn("1234567891234567");
        when(request.getParameter("cvv")).thenReturn("111");
        when(request.getParameter("sum")).thenReturn("-50");
        String path = accountRefillCommand.execute(request, response);
        assertEquals(path, ACCOUNT_REFILL_JSP);
    }
}