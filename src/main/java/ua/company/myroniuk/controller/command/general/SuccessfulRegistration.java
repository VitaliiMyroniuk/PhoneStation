package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class SuccessfulRegistration implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return SUCCESSFUL_REGISTRATION_JSP;
    }
}
