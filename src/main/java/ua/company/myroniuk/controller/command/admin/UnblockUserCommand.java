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

    UnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final UnblockUserCommand INSTANCE = new UnblockUserCommand(UserServiceImpl.getInstance());
    }

    public static UnblockUserCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("user_id"));
        userService.updateIsBlocked(userId, false);
        return "redirect:/phone_station/user_invoices?user_id=" + userId;
    }
}
