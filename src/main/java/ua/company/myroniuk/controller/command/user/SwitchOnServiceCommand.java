package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.ServiceService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.ServiceServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the service switch on process.
 *
 * @author Vitalii Myroniuk
 */
public class SwitchOnServiceCommand implements Command {
    private UserService userService;
    private ServiceService serviceService;

    public SwitchOnServiceCommand() {
        userService = UserServiceImpl.getInstance();
        serviceService = ServiceServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        userService.switchOnService(userId, serviceId);
        List<Service> allServices = serviceService.getAllServices();
        List<Service> userServices = serviceService.getUserServices(userId);
        request.setAttribute("all_services", allServices);
        request.setAttribute("user_services", userServices);
        return "redirect:/phone_station/services";
    }
}
