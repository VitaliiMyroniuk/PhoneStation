package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for deleting the new user (not yet registered user).
 *
 * @author Vitalii Myroniuk
 */
public class DeleteNewUserCommand implements Command {
    private UserService userService;

    public DeleteNewUserCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public DeleteNewUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("user_id"));
        userService.deleteUser(userId);
        return REDIRECT_TO_NEW_USERS_JSP;
    }
}
