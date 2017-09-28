package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Service;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface ServiceDao {

    long addUserService(Service service, long userId);

    Service getService(long id);

    List<Service> getAllServices();

    List<Service> getUserServices(long userId);

    boolean deleteUserService(long userId, long serviceId);

    boolean deleteUserServices(long userId);
}
