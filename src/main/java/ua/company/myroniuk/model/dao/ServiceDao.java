package ua.company.myroniuk.model.dao;

import ua.company.myroniuk.model.entity.Service;
import java.util.List;

/**
 * The interface describes the behavior of the {@code ServiceDao} object.
 *
 * @author Vitalii Myroniuk
 */
public interface ServiceDao {
    /**
     * The method activates the given service for the user with specified id.
     *
     * @param service the service to be activated.
     * @param userId id of the user.
     * @return id of the corresponding entry from the auxiliary table between users and services.
     */
    long addUserService(Service service, long userId);

    /**
     * The method retrieves the {@code Service} object with the given id from the data base.
     *
     * @param id id of the {@code Service} object.
     * @return {@code Service} object retrieved from the data base.
     */
    Service getService(long id);

    /**
     * The method retrieves all {@code Service} objects from the data base.
     *
     * @return the list of all services available for activation.
     */
    List<Service> getAllServices();

    /**
     * The method retrieves all {@code Service} objects from the data base
     * that are activated for the user with the given id.
     *
     * @param userId id of the user.
     * @return list of all services that are activated for the user with the given id.
     */
    List<Service> getUserServices(long userId);

    /**
     * The method inactivates the service for the user with the given id.
     * The corresponding entry in the auxiliary table between users and services is deleted.
     *
     * @param userId id of the user.
     * @param serviceId id of the service to be inactivated.
     * @return true if the given service is inactivated successfully.
     */
    boolean deleteUserService(long userId, long serviceId);

    /**
     * The method inactivates all services for the user with the given id.
     * The corresponding entries in the auxiliary table between users and services are deleted.
     *
     * @param userId id of the user.
     * @return true if the given services are inactivated successfully.
     */
    boolean deleteUserServices(long userId);
}
