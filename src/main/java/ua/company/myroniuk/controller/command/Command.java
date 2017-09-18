package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public interface Command {

    //TODO find out if it is better to transfer paths into the property file.
    String ACCOUNT_REFILL_JSP = "/jsp/user/account_refill.jsp";
    String ADMIN_JSP = "/jsp/admin/admin.jsp";
    String DEBTORS_JSP = "/jsp/admin/debtors.jsp";
    String ERROR_JSP = "/jsp/general/error.jsp";
    String INDEX_JSP = "/index.jsp";
    String INVOICES_JSP = "/jsp/user/invoices.jsp";
    String NEW_USERS_JSP = "/jsp/admin/new_users.jsp";
    String REGISTRATION_JSP = "/jsp/general/registration.jsp";
    String SERVICES_JSP = "/jsp/user/services.jsp";
    String SUCCESSFUL_REGISTRATION_JSP = "/jsp/general/successful_registration.jsp";
    String USER_INVOICES_JSP = "/jsp/admin/user_invoices.jsp";
    String USER_JSP = "/jsp/user/user.jsp";
    String USERS_JSP = "/jsp/admin/users.jsp";

    String execute(HttpServletRequest request, HttpServletResponse response);
}
