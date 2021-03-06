package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.DaoConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vitalii Myroniuk
 */
public class JdbcDaoConnection implements DaoConnection {
    private static final Logger LOGGER = Logger.getLogger(JdbcDaoConnection.class);
    private Connection connection;
    private volatile boolean isForTransaction = false; // variable determines if the connection is used in transaction.

    JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    Connection getConnection() {
        return connection;
    }

    @Override
    public void beginTransaction() {
        try{
            connection.setAutoCommit(false);
            isForTransaction = true;
        } catch (SQLException e){
            LOGGER.error("Error during transaction beginning: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            isForTransaction = false;
        } catch (SQLException e) {
            LOGGER.error("Error during the transaction commit: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try{
            connection.rollback();
            isForTransaction = false;
        } catch (SQLException e) {
            LOGGER.error("Error during the transaction rollback: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if(isForTransaction) {
            rollbackTransaction();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error during connection closing: ", e);
            throw new RuntimeException(e);
        }
    }
}
