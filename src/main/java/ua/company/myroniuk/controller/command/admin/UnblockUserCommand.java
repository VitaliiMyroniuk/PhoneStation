package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for unblocking user process.
 *
 * @author Vitalii Myroniuk
 */
public class UnblockUserCommand implements Command {
    private UserService userService;

    public UnblockUserCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public UnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("user_id"));
        userService.updateIsBlocked(userId, false);
        return REDIRECT_TO_USER_INVOICES_JSP + userId;
    }
}
