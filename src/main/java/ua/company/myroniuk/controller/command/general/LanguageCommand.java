package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for changing the current language.
 *
 * @author Vitalii Myroniuk
 */
public class LanguageCommand implements Command {

    LanguageCommand() {
    }

    private static class SingletonHolder {
        private static final LanguageCommand INSTANCE = new LanguageCommand();
    }

    public static LanguageCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        if ("uk_UA".equals(locale)) {
            session.setAttribute("locale", locale);
        } else if ("en_GB".equals(locale)) {
            session.setAttribute("locale", locale);
        }
        return "redirect:" + getOriginalURI(request);
    }

    private String getOriginalURI(HttpServletRequest request) {
        String requestURI = request.getParameter("requestURI");
        String queryString = request.getParameter("queryString");
        Pattern pattern = Pattern.compile("^.*?/([A-Za-z0-9_]+)\\.jsp$");
        Matcher matcher = pattern.matcher(requestURI);
        String originalURI = "/phone_station/error_404";
        if (matcher.find()) {
            originalURI = String.format("/phone_station/%s?%s", matcher.group(1), queryString);
        }
        return originalURI;
    }
}
