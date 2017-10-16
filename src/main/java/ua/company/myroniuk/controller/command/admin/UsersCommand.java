package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all registered users.
 *
 * @author Vitalii Myroniuk
 */
public class UsersCommand implements Command {
    private UserService userService;

    public UsersCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public UsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int recordsPerPage = 2;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        int from = (page - 1) * recordsPerPage;
        List<User> users = userService.getRegisteredUsers(from , recordsPerPage);
        int countOfRegisteredUsers = userService.getUserCountInfo()[0];
        int numberOfPages = (int) Math.ceil(1.0 * countOfRegisteredUsers / recordsPerPage);
        request.setAttribute("users", users);
        request.setAttribute("from", from);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        return USERS_JSP;
    }
}
