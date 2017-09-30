package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the user logout process.
 *
 * @author Vitalii Myroniuk
 */
public class LogoutCommand implements Command {

    LogoutCommand() {
    }

    private static class SingletonHolder {
        private static final LogoutCommand INSTANCE = new LogoutCommand();
    }

    public static LogoutCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/phone_station/login";
    }
}
