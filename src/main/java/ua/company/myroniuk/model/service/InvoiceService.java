package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Invoice;
import java.util.List;

/**
 * The interface describes the behavior of the {@code InvoiceService} object.
 *
 * @author Vitalii Myroniuk
 */
public interface InvoiceService {
    /**
     * The method retrieves all {@code Invoice} objects from the data base
     * for the user with the given id.
     *
     * @param userId id of the user.
     * @return the list of invoices that correspond the user with the given id.
     */
    List<Invoice> getInvoices(long userId);
}
