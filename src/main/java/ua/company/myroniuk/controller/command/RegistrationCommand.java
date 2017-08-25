package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        String phoneNumber = request.getParameter("phoneNumber");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passport = request.getParameter("passport");
        String login = request.getParameter("login");
        String password = request.getParameter("password");



        // 1) check login and phoneNumber (if something is wrong then we return REGISTRATION_JSP with warnings)
        // 2) add corresponding sim-card with standard service to the data base
        // 3) add user to the data base

        return SUCCESSFUL_REGISTRATION_JSP;
    }
}
