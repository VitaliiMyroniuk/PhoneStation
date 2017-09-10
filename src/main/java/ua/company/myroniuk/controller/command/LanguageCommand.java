package ua.company.myroniuk.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Vitalii Myroniuk
 */
public class LanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String currentPage = (String) session.getAttribute("page");
        String locale = request.getParameter("locale");
        if ("uk_UA".equals(locale)) {
            session.setAttribute("locale", locale);
        } else if ("en_GB".equals(locale)) {
            session.setAttribute("locale", locale);
        }
        return currentPage;
    }
}
