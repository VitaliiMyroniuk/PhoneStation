package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public interface Command {
    String INDEX_JSP = "/index.jsp";    //TODO find out if it is better to transfer paths into the property file.
    String USER_JSP = "/jsp/user.jsp";
    String ADMIN_JSP = "/jsp/admin.jsp";
    String REGISTRATION_JSP = "/jsp/registration.jsp";
    String SUCCESSFUL_REGISTRATION_JSP = "/jsp/successful_registration.jsp";
    String ALL_USERS_JSP = "/jsp/all_users.jsp";
    String NEW_USERS_JSP = "/jsp/new_users.jsp";
    String DEBTORS_JSP = "/jsp/debtors.jsp";
    String ERROR_JSP = "/jsp/error.jsp";

    /**
     *
     * @param request
     * @param response
     * @return
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
