package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.DBManager;
import ua.company.myroniuk.dao.ServiceDao;
import ua.company.myroniuk.model.entity.Service;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceDaoImpl implements ServiceDao {

    private final String ADD_USER_SERVICE =
            "INSERT INTO users_services (user_id, service_id, start_date, end_date) " +
            "VALUES (?, ?, ?, ?)";

    private final String GET_SERVICE_BY_ID =
            "SELECT * FROM services where id = ?";

    private final String GET_ALL_SERVICES =
            "SELECT * FROM services";

    private final String GET_USER_SERVICES =
            "SELECT * FROM users " +
            "INNER JOIN users_services ON users.id = user_id " +
            "INNER JOIN services ON service_id = services.id " +
            "WHERE users.id = ? AND NOW() BETWEEN start_date AND end_date";

    private final String DELETE_USER_SERVICE =
            "DELETE FROM users_services WHERE user_id = ? AND service_id = ?";

    private ServiceDaoImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceDaoImpl INSTANCE = new ServiceDaoImpl();
    }

    public static ServiceDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public long addUserService(Connection connection, Service service, long userId) throws SQLException {
        long userServicesId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_USER_SERVICE, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, service.getId());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now().plusDays(service.getDuration())));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userServicesId = resultSet.getLong(1);
            }
        }
        return userServicesId;
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

    @Override
    public List<Service> getUserServices(long userId) {
        List<Service> services = new ArrayList<>();
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SERVICES);
        ) {
            preparedStatement.setLong(1, userId);
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

    @Override
    public boolean deleteUserService(long userId, long serviceId) {
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SERVICE);
        ) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, serviceId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Service createService(ResultSet resultSet) throws SQLException {
        Service service = new Service();
        service.setId(resultSet.getLong("services.id"));
        service.setName(resultSet.getString("services.name"));
        service.setDescription(resultSet.getString("services.description"));
        service.setDuration(resultSet.getInt("services.duration"));
        service.setPrice(resultSet.getInt("services.price"));
        return service;
    }
}
