package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.DBConnector;
import ua.company.myroniuk.dao.ServiceDao;
import ua.company.myroniuk.model.entity.Service;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceDaoImpl implements ServiceDao {
    private final String ADD_SERVICE = "INSERT INTO services " +
            "(id, sim_card_id, name, price, start_date, end_date, is_active) " +
            "VALUES (null, ?, ?, ?, ?, ?, ?)";
    private final String GET_SERVICE_BY_ID = "SELECT * FROM services WHERE id = ?";
    private final String GET_SERVICES_BY_SIM_CARD_ID = "SELECT * FROM services WHERE sim_card_id = ?";
    private final String GET_ALL_SERVICES = "SELECT * FROM services";
    private final String UPDATE_SERVICE = "UPDATE services SET name = ?, price = ?, start_date = ?, " +
                                          "end_date = ?, is_active = ? WHERE id = ?";
    private final String DELETE_SERVICE = "DELETE FROM services WHERE id = ?";

    private ServiceDaoImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceDaoImpl INSTANCE = new ServiceDaoImpl();
    }

    public static ServiceDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Long addService(Connection connection, Service service, Long simCardId) throws SQLException {
        Long serviceId = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(ADD_SERVICE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, simCardId);
            preparedStatement.setString(2, service.getName());
            preparedStatement.setInt(3, service.getPrice());
            preparedStatement.setDate(4, Date.valueOf(service.getStartDate()));
            preparedStatement.setDate(5, Date.valueOf(service.getEndDate()));
            preparedStatement.setBoolean(6, service.isActive());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                serviceId = resultSet.getLong(1);
            }
        } finally {
            DBConnector.closeStatement(preparedStatement);
        }
        return serviceId;
    }

    @Override
    public List<Service> getServices(Connection connection, Long simCardId) throws SQLException {
        List<Service> services = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SERVICES_BY_SIM_CARD_ID);
            preparedStatement.setLong(1, simCardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Service service = new Service();
                service.setId(resultSet.getLong("id"));
                service.setName(resultSet.getString("name"));
                service.setPrice(resultSet.getInt("price"));
                service.setStartDate(resultSet.getDate("start_date").toLocalDate());
                service.setEndDate(resultSet.getDate("end_date").toLocalDate());
                service.setActive(resultSet.getBoolean("is_active"));
                services.add(service);
            }
        } finally {
            DBConnector.closeStatement(preparedStatement);
        }
        return services;
    }
}
