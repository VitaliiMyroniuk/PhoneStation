package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.AccountService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.AccountServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the user login process.
 *
 * @author Vitalii Myroniuk
 */
public class LoginCommand implements Command {
    private AccountService accountService;   // TODO refactor this dependencies.
    private UserService userService;

    LoginCommand(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final LoginCommand INSTANCE =
                new LoginCommand(AccountServiceImpl.getInstance(), UserServiceImpl.getInstance());
    }

    public static LoginCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int result = accountService.checkLoginAndPassword(login, password);
        User user = (User) request.getSession().getAttribute("user");
        if (result < 0 || user != null) {
            return LOGIN_JSP;
        } else if (result == 0) {
            user = userService.getUserByLogin(login);
            request.getSession().setAttribute("user", user);
            return "redirect:/phone_station/profile";
        } else {
            User admin = userService.getUserByLogin(login);
            request.getSession().setAttribute("user", admin);
            int[] userCountInfo = userService.getUserCountInfo();
            request.setAttribute("user_count_info", userCountInfo);
            return "redirect:/phone_station/profile";
        }
    }
}
