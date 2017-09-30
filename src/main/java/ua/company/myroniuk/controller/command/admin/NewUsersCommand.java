package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all unregistered users.
 *
 * @author Vitalii Myroniuk
 */
public class NewUsersCommand implements Command {

    private UserService userService;

    NewUsersCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final NewUsersCommand INSTANCE = new NewUsersCommand(UserServiceImpl.getInstance());
    }

    public static NewUsersCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getUnregisteredUsers();
        request.setAttribute("new_users", users);
        return NEW_USERS_JSP;
    }
}
