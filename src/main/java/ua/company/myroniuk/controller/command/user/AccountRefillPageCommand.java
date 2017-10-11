package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class AccountRefillPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return ACCOUNT_REFILL_JSP;
    }
}
