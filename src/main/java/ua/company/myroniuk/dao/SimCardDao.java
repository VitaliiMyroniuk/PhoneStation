package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.SimCard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface SimCardDao {

    /**
     *
     * @param connection
     * @param simCard
     * @return
     * @throws SQLException
     */
    Long addSimCard(Connection connection, SimCard simCard) throws SQLException;

    /**
     *
     * @param connection
     * @param id
     * @return
     * @throws SQLException
     */
    SimCard getSimCard(Connection connection, Long id) throws SQLException;
}
