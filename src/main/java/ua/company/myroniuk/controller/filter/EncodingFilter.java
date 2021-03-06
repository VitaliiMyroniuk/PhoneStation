package ua.company.myroniuk.controller.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * {@code EncodingFilter} is the class that describes character encoding filter.
 * It provides UTF-8 encoding for all servlets with url pattern {@code "/*"}.
 *
 * @author Vitalii Myroniuk
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        String requestEncoding = servletRequest.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
