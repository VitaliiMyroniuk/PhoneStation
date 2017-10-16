package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting admin profile page.
 *
 * @author Vitalii Myroniuk
 */
public class AdminProfileCommand implements Command {
    private UserService userService;

    public AdminProfileCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public AdminProfileCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int[] userCountInfo = userService.getUserCountInfo();
        request.setAttribute("user_count_info", userCountInfo);
        return ADMIN_PROFILE_JSP;
    }
}
