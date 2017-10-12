package ua.company.myroniuk.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * {@code RedirectionFilter} is the class that describes redirection filter.
 * It provides the redirection from the welcome page to the login page.
 *
 * @author Vitalii Myroniuk
 */
public class RedirectionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("/phone_station/login");
    }

    @Override
    public void destroy() {
    }
}
