package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.InvoiceDao;
import ua.company.myroniuk.model.entity.Invoice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code JdbcInvoiceDao} class is a JDBC implementation of the {@code InvoiceDao} interface.
 *
 * @author Vitalii Myroniuk
 */
public class JdbcInvoiceDao implements InvoiceDao {

    private static final String ADD_INVOICE =
            "INSERT INTO invoices (user_id, date, description, price, is_paid) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String GET_INVOICE_BY_ID =
            "SELECT * FROM invoices WHERE id = ?";

    private static final String GET_UNPAID_INVOICES_BY_USER_ID =
            "SELECT * FROM invoices WHERE user_id = ? AND is_paid = 0";

    private static final String UPDATE_IS_PAID =
            "UPDATE invoices SET is_paid = ? WHERE id = ?";

    private static final String DELETE_INVOICES =
            "DELETE FROM invoices WHERE user_id = ?";

    private static final Logger LOGGER = Logger.getLogger(JdbcInvoiceDao.class);

    private Connection connection;

    JdbcInvoiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long addInvoice(Invoice invoice, long userId) {
        long invoiceId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_INVOICE, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParameters(preparedStatement, userId, invoice);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                invoiceId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during inserting the invoice into the data base: ", e);
            throw new RuntimeException(e);
        }
        return invoiceId;
    }

    @Override
    public Invoice getInvoice(long id) {
        Invoice invoice = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_INVOICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                invoice = createInvoice(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the invoice by id: ", e);
            throw new RuntimeException(e);
        }
        return invoice;
    }

    @Override
    public List<Invoice> getInvoices(long userId) {
        List<Invoice> invoices = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_UNPAID_INVOICES_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = createInvoice(resultSet);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the invoices list by the user id: ", e);
            throw new RuntimeException(e);
        }
        return invoices;
    }

    @Override
    public boolean updateIsPaid(boolean isPaid, long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_IS_PAID)) {
            preparedStatement.setBoolean(1, isPaid);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during updating the pay status of the invoice: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteInvoices(long userId) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_INVOICES)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting user invoices: ", e);
            throw new RuntimeException(e);
        }
    }

    private Invoice createInvoice(ResultSet resultSet) throws SQLException {
        return new Invoice.Builder()
                .setId(resultSet.getLong("invoices.id"))
                .setDateTime(resultSet.getTimestamp("invoices.date").toLocalDateTime())
                .setDescription(resultSet.getString("invoices.description"))
                .setPrice(resultSet.getInt("invoices.price"))
                .setPaid(resultSet.getBoolean("invoices.is_paid"))
                .build();
    }

    private void setStatementParameters(PreparedStatement preparedStatement,
                                        long userId, Invoice invoice) throws SQLException {
        preparedStatement.setLong(1, userId);
        preparedStatement.setTimestamp(2, Timestamp.valueOf(invoice.getDateTime()));
        preparedStatement.setString(3, invoice.getDescription());
        preparedStatement.setInt(4, invoice.getPrice());
        preparedStatement.setBoolean(5, invoice.isPaid());
    }
}
