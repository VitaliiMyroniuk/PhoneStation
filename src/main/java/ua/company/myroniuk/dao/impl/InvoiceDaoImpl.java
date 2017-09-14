package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.InvoiceDao;
import ua.company.myroniuk.model.entity.Invoice;

import java.sql.*;

/**
 * @author Vitalii Myroniuk
 */
public class InvoiceDaoImpl implements InvoiceDao {
    private final String ADD_INVOICE =
            "INSERT INTO invoices (id, user_id, date, description, price, is_paid) " +
            "VALUES (null, ?, ?, ?, ?, ?)";

    private InvoiceDaoImpl() {
    }

    private static class SingletonHolder {
        private static final InvoiceDaoImpl INSTANCE = new InvoiceDaoImpl();
    }

    public static InvoiceDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addInvoice(Connection connection, Invoice invoice, long userId) throws SQLException {
        long invoiceId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_INVOICE, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setDate(2, Date.valueOf(invoice.getDate()));
            preparedStatement.setString(3, invoice.getDescription());
            preparedStatement.setInt(4, invoice.getPrice());
            preparedStatement.setBoolean(5, invoice.isPaid());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                invoiceId = resultSet.getLong(1);
            }
        }
        return invoiceId;
    }
}
