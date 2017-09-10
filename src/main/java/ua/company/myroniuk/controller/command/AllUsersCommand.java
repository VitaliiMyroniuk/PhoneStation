package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class AllUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getAccount().isAdmin()) {
            return ERROR_JSP;
        } else {
            UserService userService = UserServiceImpl.getInstance();
            List<User> users = userService.getAllUsers();
            request.getSession().setAttribute("all_users", users);
            return ALL_USERS_JSP;
        }
    }
}
