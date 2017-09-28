package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vitalii Myroniuk
 */
public class JdbcDaoFactory extends DaoFactory {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(JdbcDaoFactory.class);

    /**
     * {@code javax.sql.DataSource} object.
     */
    private DataSource dataSource;

    public JdbcDaoFactory() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/phone_station");
        } catch (NamingException e) {
            LOGGER.error("Error in looking up the data source: ", e);
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Error during the java.sql.Connection getting: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public DaoConnection getDaoConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            LOGGER.error("Error during the DaoConnection getting: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccountDao createAccountDao() {
        return new JdbcAccountDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JdbcUserDao(getConnection());
    }

    @Override
    public ServiceDao createServiceDao() {
        return new JdbcServiceDao(getConnection());
    }

    @Override
    public InvoiceDao createInvoiceDao() {
        return new JdbcInvoiceDao(getConnection());
    }

    @Override
    public AccountDao createAccountDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection connection  = jdbcDaoConnection.getConnection();
        return new JdbcAccountDao(connection);
    }

    @Override
    public UserDao createUserDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection connection  = jdbcDaoConnection.getConnection();
        return new JdbcUserDao(connection);
    }

    @Override
    public ServiceDao createServiceDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection connection  = jdbcDaoConnection.getConnection();
        return new JdbcServiceDao(connection);
    }

    @Override
    public InvoiceDao createInvoiceDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcDaoConnection = (JdbcDaoConnection) daoConnection;
        Connection connection  = jdbcDaoConnection.getConnection();
        return new JdbcInvoiceDao(connection);
    }
}
