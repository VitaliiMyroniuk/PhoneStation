package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class AccountRefillCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 1) retrieve the data from the form of jsp
        String creditCard = request.getParameter("credit_card");
        String cvv = request.getParameter("cvv");
        String sum = request.getParameter("sum");

        // TODO realize warnings in case the input is not valid
        // 2) validation of the input data
        if (sum == null || Integer.parseInt(sum) <= 0) {
            return ACCOUNT_REFILL_JSP;
        }

        // 3) account refill of the user
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        UserService userService = UserServiceImpl.getInstance();
        userService.updateBalance(userId, Integer.parseInt(sum));
        user = userService.getUserById(userId);
        request.getSession().setAttribute("user", user);
        return USER_JSP;
    }
}
