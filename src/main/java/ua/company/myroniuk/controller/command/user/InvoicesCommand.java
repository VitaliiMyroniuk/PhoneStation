package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class InvoicesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ERROR_JSP;
        } else {
            UserService userService = UserServiceImpl.getInstance();
            long userId = user.getId();
            List<Invoice> userInvoices = userService.getInvoices(userId);
            user.setInvoices(userInvoices);
            request.getSession().setAttribute("user", user);
            return INVOICES_JSP;
        }
    }
}
