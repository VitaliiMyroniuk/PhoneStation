package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Invoice;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface InvoiceDao {

    long addInvoice(Connection connection, Invoice invoice, long userId) throws SQLException;

    Invoice getInvoice(Connection connection, long id);

    List<Invoice> getInvoices(long userId);

    boolean updateIsPaid(Connection connection, boolean isPaid, long id);

    boolean deleteInvoices(Connection connection, long userId);
}
