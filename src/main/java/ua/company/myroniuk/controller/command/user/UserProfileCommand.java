package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting user profile page.
 *
 * @author Vitalii Myroniuk
 */
public class UserProfileCommand implements Command {
    private UserService userService;

    public UserProfileCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public UserProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        user = userService.getUserById(userId);
        request.getSession().setAttribute("user", user);
        return USER_PROFILE_JSP;
    }
}
