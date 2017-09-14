package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Invoice;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vitalii Myroniuk
 */
public interface InvoiceDao {
    long addInvoice(Connection connection, Invoice invoice, long userId) throws SQLException;
}
