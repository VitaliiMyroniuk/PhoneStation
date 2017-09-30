package ua.company.myroniuk.controller.command.admin;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import ua.company.myroniuk.model.service.UserService;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Vitalii Myroniuk
 */
public class BlockUserCommandTest {
    private HttpSession session;
    private HttpRequest request;
    private HttpResponse response;
    private UserService userService;
    private BlockUserCommand blockUserCommand;


    @Before
    public void init() {
        userService = mock(UserService.class);
    }

    @Test
    public void execute() throws Exception {

    }
}