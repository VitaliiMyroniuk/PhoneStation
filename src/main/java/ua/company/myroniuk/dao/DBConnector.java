package ua.company.myroniuk.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Vitalii Myroniuk
 */
public class DBConnector {

//    public static Connection getConnection() throws SQLException {
//        ResourceBundle resource = ResourceBundle.getBundle("database");
//        String url = resource.getString("db.url");
//        String user = resource.getString("db.user");
//        String password = resource.getString("db.password");
//        return DriverManager.getConnection(url, user, password);
//    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/phone_station");
            connection = dataSource.getConnection();
            System.out.println("Connection is succeed!");
        } catch (NamingException e) {
            System.out.println("NamingException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        return connection;
    }
}
