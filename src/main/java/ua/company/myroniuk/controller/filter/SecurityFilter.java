package ua.company.myroniuk.controller.filter;

import ua.company.myroniuk.model.entity.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vitalii Myroniuk
 */
@WebFilter(urlPatterns = {"/controller"})
public class SecurityFilter implements Filter {

    private Set<String> generalQueries = new HashSet<>();

    private Set<String> userQueries = new HashSet<>();

    private Set<String> adminQueries = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // general queries
        generalQueries.add("language");
        generalQueries.add("login");
        generalQueries.add("logout");
        generalQueries.add("profile");
        generalQueries.add("registration");

        // user queries
        userQueries.addAll(generalQueries);
        userQueries.add("account_refill");
        userQueries.add("invoices");
        userQueries.add("pay_invoice");
        userQueries.add("services");
        userQueries.add("switch_off_service");
        userQueries.add("switch_on_service");

        // admin queries
        adminQueries.addAll(generalQueries);
        adminQueries.add("block_user");
        adminQueries.add("debtors");
        adminQueries.add("new_users");
        adminQueries.add("register_user");
        adminQueries.add("unblock_user");
        adminQueries.add("user_invoices");
        adminQueries.add("users");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String query = request.getParameter("query");
        User user = (User) request.getSession().getAttribute("user");
        boolean isForbiddenAccess =
                user == null && !generalQueries.contains(query) ||
                user != null && !user.getAccount().isAdmin() && !userQueries.contains(query) ||
                user != null && user.getAccount().isAdmin() && !adminQueries.contains(query);
        if (isForbiddenAccess) {
            request.setAttribute("query", "profile");
        } else {
            request.setAttribute("query", query);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        generalQueries = null;
        userQueries = null;
        adminQueries = null;
    }
}
