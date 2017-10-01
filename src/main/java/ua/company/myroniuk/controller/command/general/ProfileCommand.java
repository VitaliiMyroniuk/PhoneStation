package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Role;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting the user profile.
 *
 * @author Vitalii Myroniuk
 */
public class ProfileCommand implements Command {

    private UserService userService;

    ProfileCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final ProfileCommand INSTANCE = new ProfileCommand(UserServiceImpl.getInstance());
    }

    public static ProfileCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Role role = user.getAccount().getRole();
        boolean isAdmin = Role.ADMIN.equals(role);
        if (isAdmin) {
            int[] userCountInfo = userService.getUserCountInfo();
            request.setAttribute("user_count_info", userCountInfo);
            return ADMIN_PROFILE_JSP;
        } else {
            long userId = user.getId();
            user = userService.getUserById(userId);
            request.getSession().setAttribute("user", user);
            return USER_PROFILE_JSP;
        }
    }
}
