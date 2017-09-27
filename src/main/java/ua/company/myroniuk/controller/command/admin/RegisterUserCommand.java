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
 * It contains the method for the registration user process.
 *
 * @author Vitalii Myroniuk
 */
public class RegisterUserCommand implements Command {

    private UserService userService;

    public RegisterUserCommand() {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("user_id"));
        userService.updateIsRegistered(userId);
        List<User> users = userService.getUnregisteredUsers();
        request.setAttribute("new_users", users);
        return "redirect:/phone_station/new_users";
    }
}
