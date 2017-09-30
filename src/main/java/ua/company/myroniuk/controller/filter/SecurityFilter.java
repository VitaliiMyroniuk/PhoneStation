package ua.company.myroniuk.controller.filter;

import ua.company.myroniuk.model.entity.Role;
import ua.company.myroniuk.model.entity.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * {@code SecurityFilter} is the class that describes security filter.
 * It forbids the access to resources for the users that don't have necessary rights.
 *
 * @author Vitalii Myroniuk
 */
public class SecurityFilter implements Filter {
    /**
     * Set of allowed uri's for all users.
     */
    private Set<String> generalURI = new HashSet<>();

    /**
     * Set of allowed uri's for the subscriber.
     */
    private Set<String> subscriberURI = new HashSet<>();

    /**
     * Set of allowed uri's for the admin.
     */
    private Set<String> adminURI = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // general uri
        generalURI.add("/phone_station/language");
        generalURI.add("/phone_station/login");
        generalURI.add("/phone_station/logout");
        generalURI.add("/phone_station/registration");
        generalURI.add("/phone_station/successful_registration");

        // subscriber uri
        subscriberURI.addAll(generalURI);
        subscriberURI.add("/phone_station/account_refill");
        subscriberURI.add("/phone_station/invoices");
        subscriberURI.add("/phone_station/pay_invoice");
        subscriberURI.add("/phone_station/profile");
        subscriberURI.add("/phone_station/services");
        subscriberURI.add("/phone_station/switch_off_service");
        subscriberURI.add("/phone_station/switch_on_service");

        // admin uri
        adminURI.addAll(generalURI);
        adminURI.add("/phone_station/block_user");
        adminURI.add("/phone_station/debtors");
        adminURI.add("/phone_station/delete_user");
        adminURI.add("/phone_station/new_users");
        adminURI.add("/phone_station/profile");
        adminURI.add("/phone_station/register_user");
        adminURI.add("/phone_station/unblock_user");
        adminURI.add("/phone_station/user_invoices");
        adminURI.add("/phone_station/users");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String uri = request.getRequestURI();
        boolean isAllowedGuestAccess =
                (user == null) &&
                generalURI.contains(uri);
        boolean isAllowedSubscriberAccess =
                (user != null) &&
                Role.SUBSCRIBER.equals(user.getAccount().getRole()) &&
                subscriberURI.contains(uri);
        boolean isAllowedAdminAccess =
                (user != null) &&
                Role.ADMIN.equals(user.getAccount().getRole()) &&
                adminURI.contains(uri);
        if (isAllowedGuestAccess || isAllowedSubscriberAccess || isAllowedAdminAccess) {
            request.setAttribute("uri", uri);
        } else {
            request.setAttribute("uri", "/phone_station/login");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        generalURI = null;
        subscriberURI = null;
        adminURI = null;
    }
}
