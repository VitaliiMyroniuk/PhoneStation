package ua.company.myroniuk.controller.command.general;

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
public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return INDEX_JSP;
        } else if (user.getAccount().isAdmin()) {
            UserService userService = UserServiceImpl.getInstance();
            int[] userCountInfo = userService.getUserCountInfo();
            request.setAttribute("user_count_info", userCountInfo);
            return ADMIN_JSP;
        } else {
            UserService userService = UserServiceImpl.getInstance();
            long userId = user.getId();
            user = userService.getUserById(userId);
            request.getSession().setAttribute("user", user);
            return USER_JSP;
        }
    }
}
