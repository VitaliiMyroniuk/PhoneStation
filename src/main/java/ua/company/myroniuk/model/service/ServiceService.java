package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Service;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface ServiceService {

    List<Service> getAllServices();

    List<Service> getUserServices(long userId);
}
