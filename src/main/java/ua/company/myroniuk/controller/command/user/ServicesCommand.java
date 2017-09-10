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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class ServicesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ERROR_JSP;
        } else {
            UserService userService = UserServiceImpl.getInstance();
            long userId = user.getId();
            List<Service> userServices = userService.getServices(userId);
            if (userServices.size() != 0) {
                user.setServices(userServices);
                request.getSession().setAttribute("user", user);
            }
            ServiceService serviceService = ServiceServiceImpl.getInstance();
            List<Service> allServices = serviceService.getAllServices();
            request.getSession().setAttribute("services", allServices);
            return SERVICES_JSP;
        }
    }
}
