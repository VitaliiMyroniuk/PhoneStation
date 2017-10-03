package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Invoice;
import java.util.List;

/**
 * The interface describes the behavior of the {@code InvoiceDao} object.
 *
 * @author Vitalii Myroniuk
 */
public interface InvoiceDao {
    /**
     *
     * @param invoice
     * @param userId
     * @return
     */
    long addInvoice(Invoice invoice, long userId);

    /**
     *
     * @param id
     * @return
     */
    Invoice getInvoice(long id);

    /**
     *
     * @param userId
     * @return
     */
    List<Invoice> getInvoices(long userId);

    /**
     *
     * @param isPaid
     * @param id
     * @return
     */
    boolean updateIsPaid(boolean isPaid, long id);

    /**
     *
     * @param userId
     * @return
     */
    boolean deleteInvoices(long userId);
}
