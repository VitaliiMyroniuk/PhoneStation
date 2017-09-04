package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface ServiceDao {

    /**
     *
     * @param connection
     * @param service
     * @param simCardId
     * @return
     * @throws SQLException
     */
    Long addService(Connection connection, Service service, Long simCardId) throws SQLException;

    /**
     *
     * @param connection
     * @param simCardId
     * @return
     */
    List<Service> getServices(Connection connection, Long simCardId) throws SQLException;
}
