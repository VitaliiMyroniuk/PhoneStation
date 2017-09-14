package ua.company.myroniuk.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vitalii Myroniuk
 */
@WebFilter(urlPatterns = {"/controller"})
public class CommandSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest originalRequest = (HttpServletRequest) servletRequest;
        HttpServletRequest request = new MyHttpServletRequestWrapper(originalRequest);
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
