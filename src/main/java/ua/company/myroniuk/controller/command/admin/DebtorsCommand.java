package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class DebtorsCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !user.getAccount().isAdmin()) {
            return ERROR_JSP;
        } else {
            UserService userService = UserServiceImpl.getInstance();
            List<User> users = userService.getDebtors();
            request.getSession().setAttribute("debtors", users);
            return DEBTORS_JSP;
        }
    }
}
