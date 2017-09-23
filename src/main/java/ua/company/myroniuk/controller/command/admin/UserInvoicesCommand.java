package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class UserInvoicesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        long userId = Long.parseLong(request.getParameter("user_id"));
        User user = userService.getUserWithInvoicesById(userId);
        int debt = userService.getDebt(user);
        request.setAttribute("subscriber", user);
        request.setAttribute("debt", debt);
        return USER_INVOICES_JSP;
    }
}
