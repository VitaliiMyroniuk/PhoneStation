package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.AccountService;
import ua.company.myroniuk.model.service.impl.AccountServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        AccountService accountService = AccountServiceImpl.getInstance();
        int result = accountService.checkLoginAndPassword(login, password);
        Boolean isOnline = (Boolean) request.getSession().getAttribute("is_online");
        if (isOnline != null || result < 0) {  //TODO try to refactor
            return INDEX_JSP;
        } else if (result == 0) {
            User user = UserServiceImpl.getInstance().getUser(login);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("is_online", true);
            return USER_JSP;
        } else {
            User user = UserServiceImpl.getInstance().getUser(login);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("is_online", true);
            return ADMIN_JSP;
        }
    }
}
