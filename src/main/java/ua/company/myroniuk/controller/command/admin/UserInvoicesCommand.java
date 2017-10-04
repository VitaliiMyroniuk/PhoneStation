package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all unpaid user invoices.
 *
 * @author Vitalii Myroniuk
 */
public class UserInvoicesCommand implements Command {

    private UserService userService;

    private UserInvoicesCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final UserInvoicesCommand INSTANCE = new UserInvoicesCommand(UserServiceImpl.getInstance());
    }

    public static UserInvoicesCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("user_id"));
        User user = userService.getUserWithUnpaidInvoicesById(userId);
        int debt = userService.getDebt(user);
        request.setAttribute("subscriber", user);
        request.setAttribute("debt", debt);
        return USER_INVOICES_JSP;
    }
}
