package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public interface Command {
    String INDEX_JSP = "/index.jsp";
    String USER_JSP = "/jsp/user.jsp";
    String ADMIN_JSP = "/jsp/admin.jsp";
    String REGISTRATION_JSP = "/jsp/registration.jsp";
    String SUCCESSFUL_REGISTRATION_JSP = "/jsp/successful_registration.jsp";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
