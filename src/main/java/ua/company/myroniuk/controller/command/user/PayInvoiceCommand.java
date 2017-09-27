package ua.company.myroniuk.controller.command.user;

import org.apache.log4j.Logger;
import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.service.InvoiceService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.InvoiceServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the invoice payment process.
 *
 * @author Vitalii Myroniuk
 */
public class PayInvoiceCommand implements Command {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PayInvoiceCommand.class);

    private UserService userService = UserServiceImpl.getInstance();  // TODO refactor this dependencies.
    private InvoiceService invoiceService = InvoiceServiceImpl.getInstance();

    public PayInvoiceCommand() {
        userService = UserServiceImpl.getInstance();
        invoiceService = InvoiceServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        long userId = user.getId();
        long invoiceId = Long.parseLong(request.getParameter("invoice_id"));
        try {
            userService.payInvoice(userId, invoiceId);
        } catch (NotEnoughMoneyException e) {
            LOGGER.error("Error during invoice payment (not enough money): ", e);
            return "redirect:/phone_station/invoices?not_enough_money=true";
        }
        List<Invoice> invoices = invoiceService.getInvoices(userId);
        request.setAttribute("invoices", invoices);
        return "redirect:/phone_station/invoices";
    }
}
