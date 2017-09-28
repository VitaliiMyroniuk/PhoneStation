package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Invoice;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface InvoiceDao {

    long addInvoice(Invoice invoice, long userId);

    Invoice getInvoice(long id);

    List<Invoice> getInvoices(long userId);

    boolean updateIsPaid(boolean isPaid, long id);

    boolean deleteInvoices(long userId);
}
