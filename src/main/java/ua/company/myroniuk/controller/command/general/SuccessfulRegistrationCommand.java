package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting jsp page of the successful registration.
 *
 * @author Vitalii Myroniuk
 */
public class SuccessfulRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return SUCCESSFUL_REGISTRATION_JSP;
    }
}
