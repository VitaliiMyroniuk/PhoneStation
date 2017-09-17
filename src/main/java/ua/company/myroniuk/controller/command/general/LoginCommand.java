package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.AccountService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.AccountServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        AccountService accountService = AccountServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int result = accountService.checkLoginAndPassword(login, password);
        User user = (User) request.getSession().getAttribute("user");
        if (result < 0 || user != null) {
            return INDEX_JSP;
        } else if (result == 0) {
            user = userService.getUserByLogin(login);
            request.getSession().setAttribute("user", user);
            return USER_JSP;
        } else {
            User admin = userService.getUserByLogin(login);
            request.getSession().setAttribute("user", admin);
            int[] userCountInfo = userService.getUserCountInfo();
            request.setAttribute("user_count_info", userCountInfo);
            return ADMIN_JSP;
        }
    }
}
