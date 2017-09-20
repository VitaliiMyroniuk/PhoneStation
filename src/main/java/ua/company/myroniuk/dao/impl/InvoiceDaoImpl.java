package ua.company.myroniuk.dao.impl;

import org.apache.log4j.Logger;
import ua.company.myroniuk.dao.DBManager;
import ua.company.myroniuk.dao.InvoiceDao;
import ua.company.myroniuk.model.entity.Invoice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class InvoiceDaoImpl implements InvoiceDao {

    private static final Logger LOGGER = Logger.getLogger(InvoiceDaoImpl.class);

    private final String ADD_INVOICE =
            "INSERT INTO invoices (user_id, date, description, price, is_paid) " +
            "VALUES (?, ?, ?, ?, ?)";

    private final String GET_INVOICE_BY_ID =
            "SELECT * FROM invoices WHERE id = ?";


    private final String GET_UNPAID_INVOICES_BY_USER_ID =
            "SELECT * FROM invoices WHERE user_id = ? AND is_paid = 0";

    private final String UPDATE_IS_PAID =
            "UPDATE invoices SET is_paid = ? WHERE id = ?";

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
            preparedStatement.setTimestamp(2, Timestamp.valueOf(invoice.getDateTime()));
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

    @Override
    public Invoice getInvoice(Connection connection, long id) {
        Invoice invoice = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_INVOICE_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                invoice = createInvoice(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the invoice by id: ", e);
        }
        return invoice;
    }

    @Override
    public List<Invoice> getInvoices(long userId) {
        List<Invoice> invoices = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_UNPAID_INVOICES_BY_USER_ID);
        ) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Invoice invoice = createInvoice(resultSet);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the invoices list by the user id: ", e);
        }
        return invoices;
    }

    @Override
    public boolean updateIsPaid(Connection connection, boolean isPaid, long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_IS_PAID);
        ) {
            preparedStatement.setBoolean(1, isPaid);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during updating the pay status of the invoice: ", e);
        }
        return false;
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setId(resultSet.getLong("invoices.id"));
        invoice.setDateTime(resultSet.getTimestamp("invoices.date").toLocalDateTime());
        invoice.setDescription(resultSet.getString("invoices.description"));
        invoice.setPrice(resultSet.getInt("invoices.price"));
        invoice.setPaid(resultSet.getBoolean("invoices.is_paid"));
        return invoice;
    }

}
