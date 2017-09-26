package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for changing the current language.
 *
 * @author Vitalii Myroniuk
 */
public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        if ("uk_UA".equals(locale)) {
            session.setAttribute("locale", locale);
        } else if ("en_GB".equals(locale)) {
            session.setAttribute("locale", locale);
        }
        String requestURI = request.getParameter("requestURI");
        String originalURI = getOriginalURI(requestURI);
        String queryString = request.getParameter("queryString");
        return String.format("redirect:%s?%s", originalURI, queryString);
    }

    private String getOriginalURI(String requestURI) {
        String originalURI;
        switch (requestURI) {
            case "/WEB-INF/view/jsp/user/account_refill.jsp" :
                originalURI = "/phone_station/account_refill";
                break;
            case "/WEB-INF/view/jsp/admin/admin.jsp" :
                originalURI = "/phone_station/profile";
                break;
            case "/WEB-INF/view/jsp/admin/debtors.jsp" :
                originalURI = "/phone_station/debtors";
                break;
            case "/WEB-INF/view/jsp/general/error.jsp" :
                originalURI = "/phone_station/error";        // TODO fix this case
                break;
            case "/index.jsp" :
                originalURI = "/phone_station/login";
                break;
            case "/WEB-INF/view/jsp/user/invoices.jsp" :
                originalURI = "/phone_station/invoices";
                break;
            case "/WEB-INF/view/jsp/admin/new_users.jsp" :
                originalURI = "/phone_station/new_users";
                break;
            case "/WEB-INF/view/jsp/general/registration.jsp" :
                originalURI = "/phone_station/registration";
                break;
            case "/WEB-INF/view/jsp/user/services.jsp" :
                originalURI = "/phone_station/services";
                break;
            case "/WEB-INF/view/jsp/general/successful_registration.jsp" :
                originalURI = "/phone_station/successful_registration";
                break;
            case "/WEB-INF/view/jsp/admin/user_invoices.jsp" :
                originalURI = "/phone_station/user_invoices";
                break;
            case "/WEB-INF/view/jsp/user/user.jsp" :
                originalURI = "/phone_station/profile";
                break;
            case "/WEB-INF/view/jsp/admin/users.jsp" :
                originalURI = "/phone_station/users";
                break;
            default:
                originalURI = "/phone_station/error_404";
                break;
        }
        return originalURI;
    }
}
