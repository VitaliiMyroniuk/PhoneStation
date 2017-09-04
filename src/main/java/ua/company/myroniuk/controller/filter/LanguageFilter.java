package ua.company.myroniuk.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
@WebFilter(urlPatterns = {"/*"})
public class LanguageFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String locale = httpRequest.getParameter("locale");
        if (locale == null || locale.equals("uk_UA")) {
            httpRequest.getSession().setAttribute("locale","uk_UA");
        } else if (locale.equals("en_GB")) {
            httpRequest.getSession().setAttribute("locale","en_GB");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
