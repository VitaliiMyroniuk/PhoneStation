package ua.company.myroniuk.controller.command.user;

import org.apache.log4j.Logger;
import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.NotEnoughMoneyException;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the invoice payment process.
 *
 * @author Vitalii Myroniuk
 */
public class PayInvoiceCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(PayInvoiceCommand.class);

    private UserService userService = UserServiceImpl.getInstance();

    private PayInvoiceCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final PayInvoiceCommand INSTANCE = new PayInvoiceCommand(UserServiceImpl.getInstance());
    }

    public static PayInvoiceCommand getInstance() {
        return SingletonHolder.INSTANCE;
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
        return "redirect:/phone_station/invoices";
    }
}
