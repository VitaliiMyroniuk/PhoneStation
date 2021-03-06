package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the service switch off process.
 *
 * @author Vitalii Myroniuk
 */
public class SwitchOffServiceCommand implements Command {
    private UserService userService;

    public SwitchOffServiceCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public SwitchOffServiceCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        userService.switchOffService(userId, serviceId);
        return REDIRECT_TO_SERVICES_JSP;
    }
}
