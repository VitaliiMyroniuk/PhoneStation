package ua.company.myroniuk.model.tag;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class {@code DateFormatTag} represents a custom tag
 * that is used to format the date depending on local attribute.
 *
 * @author Vitalii Myroniuk
 */
public class DateFormatTag extends TagSupport {
    private static final Logger LOGGER = Logger.getLogger(DateFormatTag.class);
    private LocalDateTime dateTime;
    private String locale;

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        DateTimeFormatter dateTimeFormatter;
        switch (locale) {
            case "en_GB" :
                dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                break;
            case "uk_UA" :
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                break;
            default:
                dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                break;
        }
        try {
            pageContext.getOut().write(dateTimeFormatter.format(dateTime));
        } catch (IOException e) {
            LOGGER.error("Error during the output of the formatted date: ", e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
