package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.ServiceService;
import ua.company.myroniuk.model.service.impl.ServiceServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all user services.
 *
 * @author Vitalii Myroniuk
 */
public class ServicesCommand implements Command {
    private ServiceService serviceService;

    public ServicesCommand() {
        serviceService = ServiceServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        List<Service> allServices = serviceService.getAllServices();
        List<Service> userServices = serviceService.getUserServices(userId);
        request.setAttribute("all_services", allServices);
        request.setAttribute("user_services", userServices);
        return SERVICES_JSP;
    }
}
