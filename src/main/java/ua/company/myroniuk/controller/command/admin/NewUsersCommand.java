package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class NewUsersCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        List<User> users = userService.getUnregisteredUsers();
        request.getSession().setAttribute("new_users", users);
        return NEW_USERS_JSP;
    }
}
