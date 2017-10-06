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
     * The method inserts the given {@code Invoice} object into the data base.
     *
     * @param invoice the given {@code Invoice} object.
     * @param userId id of the user who has received the given invoice.
     * @return id of the {@code Invoice} object inserted into the data base.
     */
    long addInvoice(Invoice invoice, long userId);

    /**
     * The method retrieves an {@code Invoice} object from the data base.
     *
     * @param id of {@code Invoice} object that should be retrieved from the data base.
     * @return {@code Invoice} object retrieved from the data base.
     */
    Invoice getInvoice(long id);

    /**
     * The method retrieves all {@code Invoice} objects from the data base
     * for the user with the given id.
     *
     * @param userId id of the user.
     * @return the list of invoices that correspond the user with the given id.
     */
    List<Invoice> getInvoices(long userId);

    /**
     * The method updates the paid status of the {@code Invoice} objects with the given id.
     *
     * @param isPaid true if the invoice is paid, false otherwise.
     * @param id id of the invoice that has to be paid.
     * @return true if the invoice is paid.
     */
    boolean updateIsPaid(boolean isPaid, long id);

    /**
     * The method deletes the {@code Invoice} objects from the data base
     * that corresponds the user with the given id.
     *
     * @param userId id of the user.
     * @return true if the invoices are deleted from the data base.
     */
    boolean deleteInvoices(long userId);
}
