package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.DBConnector;
import ua.company.myroniuk.dao.SimCardDao;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.SimCard;

import javax.naming.NamingException;
import java.sql.*;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class SimCardDaoImpl implements SimCardDao {
    private final String ADD_SIM_CARD = "INSERT INTO sim_cards (id, number, balance, is_registered, is_blocked) " +
                                        "VALUES (null, ?, ?, ?, ?)";
    private final String GET_SIM_CARD_BY_ID = "SELECT * FROM sim_cards WHERE id = ?";
    private final String GET_SIM_CARD_BY_NUMBER = "SELECT * FROM sim_cards WHERE number = ?";

    private SimCardDaoImpl() {
    }

    private static class SingletonHolder {
        private static final SimCardDaoImpl INSTANCE = new SimCardDaoImpl();
    }

    public static SimCardDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public Long addSimCard(Connection connection, SimCard simCard) throws SQLException {
        Long simCardId = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(ADD_SIM_CARD, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, simCard.getNumber());
            preparedStatement.setInt(2, simCard.getBalance());
            preparedStatement.setBoolean(3, simCard.isRegistered());
            preparedStatement.setBoolean(4, simCard.isBlocked());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next()) {
                simCardId = resultSet.getLong(1);
                for (Service service : simCard.getServices()) {
                    ServiceDaoImpl.getInstance().addService(connection, service, simCardId);
                }
            }
        } finally {
            DBConnector.closeStatement(preparedStatement);
        }
        return simCardId;
    }

    @Override
    public SimCard getSimCard(Connection connection, Long id) throws SQLException {
        SimCard simCard = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(GET_SIM_CARD_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet != null && resultSet.next()) {
                simCard = new SimCard();
                Long simCardId = resultSet.getLong("id");
                simCard.setId(simCardId);
                simCard.setNumber(resultSet.getString("number"));
                simCard.setBalance(resultSet.getInt("balance"));
                simCard.setRegistered(resultSet.getBoolean("is_registered"));
                simCard.setBlocked(resultSet.getBoolean("is_blocked"));
                List<Service> services = ServiceDaoImpl.getInstance().getServices(connection, simCardId);
                simCard.setServices(services);
            }
        } finally {
            DBConnector.closeStatement(preparedStatement);
        }
        return simCard;
    }
}
