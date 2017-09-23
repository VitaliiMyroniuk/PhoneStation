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

    private Set<String> subscriberQueries = new HashSet<>();

    private Set<String> adminQueries = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // general queries
        generalQueries.add("language");
        generalQueries.add("login");
        generalQueries.add("logout");
        generalQueries.add("profile");
        generalQueries.add("registration");

        // subscriber queries
        subscriberQueries.addAll(generalQueries);
        subscriberQueries.add("account_refill");
        subscriberQueries.add("invoices");
        subscriberQueries.add("pay_invoice");
        subscriberQueries.add("services");
        subscriberQueries.add("switch_off_service");
        subscriberQueries.add("switch_on_service");

        // admin queries
        adminQueries.addAll(generalQueries);
        adminQueries.add("block_user");
        adminQueries.add("debtors");
        adminQueries.add("delete_user");
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
        boolean isAllowedGuestAccess =
                (user == null) &&
                generalQueries.contains(query);
        boolean isAllowedSubscriberAccess =
                (user != null) &&
                subscriberQueries.contains(query) &&
                "SUBSCRIBER".equals(user.getAccount().getRole().toString());
        boolean isAllowedAdminAccess =
                (user != null) &&
                adminQueries.contains(query) &&
                "ADMIN".equals(user.getAccount().getRole().toString());
        if (isAllowedGuestAccess || isAllowedSubscriberAccess || isAllowedAdminAccess) {
            request.setAttribute("query", query);
        } else {
            request.setAttribute("query", "profile");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        generalQueries = null;
        subscriberQueries = null;
        adminQueries = null;
    }
}
