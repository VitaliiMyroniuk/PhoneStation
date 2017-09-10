package ua.company.myroniuk.dao;

import ua.company.myroniuk.model.entity.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface ServiceDao {

    List<Service> getAllServices();
}
