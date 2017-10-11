package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Service;
import java.util.List;

/**
 * The interface describes the behavior of the {@code ServiceService} object.
 *
 * @author Vitalii Myroniuk
 */
public interface ServiceService {
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
}
