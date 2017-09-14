package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.DBManager;
import ua.company.myroniuk.dao.ServiceDao;
import ua.company.myroniuk.model.entity.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceDaoImpl implements ServiceDao {
    private final String GET_ALL_SERVICES =
            "SELECT * FROM services";
    private final String GET_SERVICE_BY_ID =
            "SELECT * FROM services where id = ?";
    private final String UPDATE_SERVICE =
            "UPDATE services SET name = ?, price = ?, start_date = ?, " +
            "end_date = ?, is_active = ? WHERE id = ?";
    private final String DELETE_SERVICE =
            "DELETE FROM services WHERE id = ?";

    private ServiceDaoImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceDaoImpl INSTANCE = new ServiceDaoImpl();
    }

    public static ServiceDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Service getService(Connection connection, long id) throws SQLException {
        Service service = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SERVICE_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                service = createService(resultSet);
            }
        }
        return service;
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SERVICES);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Service user = createService(resultSet);
                services.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }

    private Service createService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("services.id"));
        service.setName(resultSet.getString("services.name"));
        service.setDescription(resultSet.getString("services.description"));
        service.setPrice(resultSet.getInt("services.price"));
        return service;
    }
}
