package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.InvoiceService;
import ua.company.myroniuk.model.service.impl.InvoiceServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all unpaid user invoices.
 *
 * @author Vitalii Myroniuk
 */
public class InvoicesCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        InvoiceService invoiceService = InvoiceServiceImpl.getInstance();
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        List<Invoice> invoices = invoiceService.getInvoices(userId);
        request.setAttribute("invoices", invoices);
        return INVOICES_JSP;
    }
}
