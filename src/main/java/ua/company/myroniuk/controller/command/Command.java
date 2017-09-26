package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface describes the behavior of the {@code Command} object.
 *
 * @author Vitalii Myroniuk
 */
public interface Command {
    String ACCOUNT_REFILL_JSP = "/WEB-INF/view/jsp/user/account_refill.jsp";
    String ADMIN_JSP = "/WEB-INF/view/jsp/admin/admin.jsp";
    String DEBTORS_JSP = "/WEB-INF/view/jsp/admin/debtors.jsp";
    String INDEX_JSP = "/index.jsp";
    String INVOICES_JSP = "/WEB-INF/view/jsp/user/invoices.jsp";
    String NEW_USERS_JSP = "/WEB-INF/view/jsp/admin/new_users.jsp";
    String REGISTRATION_JSP = "/WEB-INF/view/jsp/general/registration.jsp";
    String SERVICES_JSP = "/WEB-INF/view/jsp/user/services.jsp";
    String SUCCESSFUL_REGISTRATION_JSP = "/WEB-INF/view/jsp/general/successful_registration.jsp";
    String USER_INVOICES_JSP = "/WEB-INF/view/jsp/admin/user_invoices.jsp";
    String USER_JSP = "/WEB-INF/view/jsp/user/user.jsp";
    String USERS_JSP = "/WEB-INF/view/jsp/admin/users.jsp";

    /**
     * The method performs some action depending on the {@code Command} object.
     *
     * @param request http servlet request.
     * @param response http servlet response.
     * @return the string that describes the path to the corresponding jsp page.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
