package ua.company.myroniuk.model.dao.impl.jdbc;

import org.apache.log4j.Logger;
import ua.company.myroniuk.model.dao.ServiceDao;
import ua.company.myroniuk.model.entity.Service;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code JdbcServiceDao} class is a JDBC implementation of the {@code ServiceDao} interface.
 *
 * @author Vitalii Myroniuk
 */
public class JdbcServiceDao implements ServiceDao {

    private static final String ADD_USER_SERVICE =
            "INSERT INTO users_services (user_id, service_id, start_date, end_date) " +
            "VALUES (?, ?, ?, ?)";

    private static final String GET_SERVICE_BY_ID =
            "SELECT * FROM services where id = ?";

    private static final String GET_ALL_SERVICES =
            "SELECT * FROM services";

    private static final String GET_USER_SERVICES =
            "SELECT * FROM users " +
            "INNER JOIN users_services ON users.id = user_id " +
            "INNER JOIN services ON service_id = services.id " +
            "WHERE users.id = ? AND NOW() BETWEEN start_date AND end_date";

    private static final String DELETE_USER_SERVICE =
            "DELETE FROM users_services WHERE user_id = ? AND service_id = ?";

    private static final String DELETE_USER_SERVICES =
            "DELETE FROM users_services WHERE user_id = ?";

    private static final Logger LOGGER = Logger.getLogger(JdbcServiceDao.class);

    private Connection connection;

    JdbcServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long addUserService(Service service, long userId) {
        long userServicesId = -1;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(ADD_USER_SERVICE, Statement.RETURN_GENERATED_KEYS)) {
            setStatementParameters(preparedStatement, userId, service);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                userServicesId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during inserting the service into the data base: ", e);
            throw new RuntimeException(e);
        }
        return userServicesId;
    }

    @Override
    public Service getService(long id) {
        Service service = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_SERVICE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                service = createService(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the service from the data base: ", e);
            throw new RuntimeException(e);
        }
        return service;
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_ALL_SERVICES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Service user = createService(resultSet);
                services.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the list of all services: ", e);
            throw new RuntimeException(e);
        }
        return services;
    }

    @Override
    public List<Service> getUserServices(long userId) {
        List<Service> services = new ArrayList<>();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_USER_SERVICES)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Service user = createService(resultSet);
                services.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Error during getting the switched on services of the user: ", e);
            throw new RuntimeException(e);
        }
        return services;
    }

    @Override
    public boolean deleteUserService(long userId, long serviceId) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_USER_SERVICE)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, serviceId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the user service: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUserServices(long userId) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_USER_SERVICES)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Error during deleting the user services: ", e);
            throw new RuntimeException(e);
        }
    }

    private Service createService(ResultSet resultSet) throws SQLException {
        return new Service.Builder()
                .setId(resultSet.getLong("services.id"))
                .setName(resultSet.getString("services.name"))
                .setDescription(resultSet.getString("services.description"))
                .setDuration(resultSet.getInt("services.duration"))
                .setPrice(resultSet.getInt("services.price"))
                .build();
    }

    private void setStatementParameters(PreparedStatement preparedStatement,
                                        long userId, Service service) throws SQLException {
        preparedStatement.setLong(1, userId);
        preparedStatement.setLong(2, service.getId());
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
        preparedStatement.setDate(4, Date.valueOf(LocalDate.now().plusDays(service.getDuration())));
    }
}
