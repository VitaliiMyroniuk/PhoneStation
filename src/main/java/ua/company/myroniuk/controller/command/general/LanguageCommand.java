package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for changing the current language.
 *
 * @author Vitalii Myroniuk
 */
public class LanguageCommand implements Command {
    private static final String PAGE_PATH_REGEX = "^.*?/([A-Za-z0-9_]+)\\.jsp$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("locale");
        Cookie cookie = new Cookie("locale", locale);
        cookie.setMaxAge(60 * 60 * 24);    // 24 hours
        response.addCookie(cookie);
        return "redirect:" + getURI(request);
    }

    private String getURI(HttpServletRequest request) {
        String pagePath = request.getParameter("pagePath");
        String queryString = request.getParameter("queryString");
        Pattern pattern = Pattern.compile(PAGE_PATH_REGEX);
        Matcher matcher = pattern.matcher(pagePath);
        String URI = "/phone_station/error_404";
        if (matcher.find()) {
            URI = String.format("/phone_station/%s?%s", matcher.group(1), queryString);
        }
        return URI;
    }
}
