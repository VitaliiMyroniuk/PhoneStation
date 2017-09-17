package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.InvoiceService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.InvoiceServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class UserInvoicesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        InvoiceService invoiceService = InvoiceServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        long userId = Long.parseLong(request.getParameter("user_id"));
        User user = userService.getUserById(userId);
        List<Invoice> invoices = invoiceService.getInvoices(userId);
        request.setAttribute("user_invoices", invoices);
        request.setAttribute("subscriber", user);
        return USER_INVOICES_JSP;
    }
}
