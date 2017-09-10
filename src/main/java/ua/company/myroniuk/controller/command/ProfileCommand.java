package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Vitalii Myroniuk
 */
public class ProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return INDEX_JSP;
        } else if (user.getAccount().isAdmin()) {
            return ADMIN_JSP;
        } else {
            return USER_JSP;
        }
    }
}
