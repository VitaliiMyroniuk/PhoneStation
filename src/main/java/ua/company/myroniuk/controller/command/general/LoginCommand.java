package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the user login process.
 *
 * @author Vitalii Myroniuk
 */
public class LoginCommand implements Command {
    private UserService userService;

    public LoginCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser != null) {
            return "redirect:/phone_station/profile";
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.logIn(login, password);
        if (user == null) {
            return LOGIN_JSP;
        }
        request.getSession().setAttribute("user", user);
        return "redirect:/phone_station/profile";
    }
}
