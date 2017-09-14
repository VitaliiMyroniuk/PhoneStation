package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class SwitchOnServiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        UserService userService = UserServiceImpl.getInstance();
        long result = userService.addService(userId, serviceId);
        if (result > 0) {
            List<Service> userServices = userService.getServices(userId);
            user.setServices(userServices);
            request.getSession().setAttribute("user", user);
        }
        return SERVICES_JSP;
    }
}
