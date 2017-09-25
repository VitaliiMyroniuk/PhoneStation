package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Role;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        Role role = user.getAccount().getRole();
        boolean isAdmin = "ADMIN".equals(role.toString());
        if (isAdmin) {
            int[] userCountInfo = userService.getUserCountInfo();
            request.setAttribute("user_count_info", userCountInfo);
            return ADMIN_JSP;
        } else {
            long userId = user.getId();
            user = userService.getUserById(userId);
            request.getSession().setAttribute("user", user);
            return USER_JSP;
        }
    }
}
