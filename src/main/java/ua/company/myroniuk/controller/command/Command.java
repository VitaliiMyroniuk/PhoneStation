package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface describes the behavior of the {@code Command} object.
 *
 * @author Vitalii Myroniuk
 */
public interface Command {
    // forward
    String ACCOUNT_REFILL_JSP = "/WEB-INF/view/jsp/user/account_refill.jsp";
    String ADMIN_PROFILE_JSP = "/WEB-INF/view/jsp/admin/admin_profile.jsp";
    String DEBTORS_JSP = "/WEB-INF/view/jsp/admin/debtors.jsp";
    String INVOICES_JSP = "/WEB-INF/view/jsp/user/invoices.jsp";
    String LOGIN_JSP = "/WEB-INF/view/jsp/general/login.jsp";
    String NEW_USERS_JSP = "/WEB-INF/view/jsp/admin/new_users.jsp";
    String REGISTRATION_JSP = "/WEB-INF/view/jsp/general/registration.jsp";
    String SERVICES_JSP = "/WEB-INF/view/jsp/user/services.jsp";
    String SUCCESSFUL_REGISTRATION_JSP = "/WEB-INF/view/jsp/general/successful_registration.jsp";
    String USER_INVOICES_JSP = "/WEB-INF/view/jsp/admin/user_invoices.jsp";
    String USER_PROFILE_JSP = "/WEB-INF/view/jsp/user/user_profile.jsp";
    String USERS_JSP = "/WEB-INF/view/jsp/admin/users.jsp";

    // redirect
    String REDIRECT_TO_ADMIN_PROFILE_JSP = "redirect:/phone_station/admin_profile";
    String REDIRECT_TO_INVOICES_JSP = "redirect:/phone_station/invoices";
    String REDIRECT_TO_LOGIN_JSP = "redirect:/phone_station/login";
    String REDIRECT_TO_SERVICES_JSP = "redirect:/phone_station/services";
    String REDIRECT_TO_SUCCESSFUL_REGISTRATION_JSP = "redirect:/phone_station/successful_registration";
    String REDIRECT_TO_NEW_USERS_JSP = "redirect:/phone_station/new_users";
    String REDIRECT_TO_USERS_JSP = "redirect:/phone_station/users";
    String REDIRECT_TO_USER_INVOICES_JSP = "redirect:/phone_station/user_invoices?user_id=";
    String REDIRECT_TO_USER_PROFILE_JSP = "redirect:/phone_station/user_profile";

    /**
     * The method performs some action depending on the {@code Command} object.
     *
     * @param request http servlet request.
     * @param response http servlet response.
     * @return the string that describes the path to the corresponding jsp page.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
