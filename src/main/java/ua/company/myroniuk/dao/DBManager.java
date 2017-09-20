package ua.company.myroniuk.dao;

import org.apache.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The class describes the data base manager.
 * It contains useful methods for data base interaction.
 *
 * @author Vitalii Myroniuk
 */
public class DBManager {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DBManager.class);

    /**
     * The method gets the connection to the data base from the connection pool.
     *
     * @return connection taken from the connection pool.
     */
    public static Connection getConnection()  {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/phone_station");
            return dataSource.getConnection();
        } catch (NamingException e) {
            LOGGER.error("Error in looking up the data source: ", e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            LOGGER.error("Error during the connection getting: ", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * The method rolls back the current transaction for the given connection.
     *
     * @param connection taken from the connection pool.
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Error during the transaction rollback: ", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * The method closes the given connection.
     *
     * @param connection taken from the connection pool.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error during the connection closing: ", e);
                throw new RuntimeException(e);
            }
        }
    }
}
