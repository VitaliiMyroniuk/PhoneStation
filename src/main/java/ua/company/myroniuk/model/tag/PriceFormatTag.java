package ua.company.myroniuk.model.tag;

import org.apache.log4j.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The class {@code PriceFormatTag} represents a custom tag
 * that is used to format the price from coins to paper currency.
 *
 * @author Vitalii Myroniuk
 */
public class PriceFormatTag extends TagSupport {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(PriceFormatTag.class);

    /**
     * Price.
     */
    private int price;

    // Setter.
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int doStartTag() throws JspException {
        String formattedPrice = String.format("%.2f", price / 100.0);
        try {
            pageContext.getOut().write(formattedPrice);
        } catch (IOException e) {
            LOGGER.error("Error during the output of the formatted price: ", e);
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}
