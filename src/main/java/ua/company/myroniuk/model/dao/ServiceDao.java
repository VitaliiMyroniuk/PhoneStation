package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface ServiceDao {

    long addUserService(Connection connection, Service service, long userId) throws SQLException;

    Service getService(Connection connection, long id) throws SQLException;

    List<Service> getAllServices();

    List<Service> getUserServices(long userId);

    boolean deleteUserService(long userId, long serviceId);

    boolean deleteUserServices(Connection connection, long userId);
}
