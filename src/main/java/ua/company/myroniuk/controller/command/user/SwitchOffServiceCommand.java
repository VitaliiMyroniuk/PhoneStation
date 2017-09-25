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
 * @author Vitalii Myroniuk
 */
public class SwitchOffServiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        ServiceService serviceService = ServiceServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long serviceId = Long.parseLong(request.getParameter("service_id"));
        userService.switchOffService(userId, serviceId);
        List<Service> allServices = serviceService.getAllServices();
        List<Service> userServices = serviceService.getUserServices(userId);
        request.setAttribute("all_services", allServices);
        request.setAttribute("user_services", userServices);
        return "redirect:/phone_station/services";
    }
}
