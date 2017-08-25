package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class AuthenticationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        byte checkLogin = userService.checkLogin(login, password);
        if (checkLogin == 0) {
            return USER_JSP;
        } else if (checkLogin > 0) {
            return ADMIN_JSP;
        } else {
            return INDEX_JSP;
        }
    }
}
