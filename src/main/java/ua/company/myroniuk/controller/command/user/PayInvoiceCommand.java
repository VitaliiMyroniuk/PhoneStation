package ua.company.myroniuk.controller.command.user;

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
public class PayInvoiceCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = UserServiceImpl.getInstance();
        InvoiceService invoiceService = InvoiceServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long invoiceId = Long.parseLong(request.getParameter("invoice_id"));
        userService.payInvoice(userId, invoiceId);
        List<Invoice> invoices = invoiceService.getInvoices(userId);
        request.setAttribute("invoices", invoices);
        return INVOICES_JSP;
    }
}