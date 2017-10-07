package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.DaoConnection;
import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.dao.ServiceDao;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.service.ServiceService;
import java.util.List;

/**
 * The class represents the service for the {@code Service} object.
 * It implements {@code ServiceService} interface.
 *
 * @author Vitalii Myroniuk
 */
public class ServiceServiceImpl implements ServiceService {
    private DaoFactory daoFactory;

    private ServiceServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    /**
     * The class {@code SingletonHolder} is the auxiliary static nested class
     * for the thread safe (Bill Pugh) singleton implementation.
     */
    private static class SingletonHolder {
        private static final ServiceServiceImpl INSTANCE = new ServiceServiceImpl();
    }

    /**
     * The methods provides creating or getting already created {@code ServiceServiceImpl} object.
     *
     * @return {@code ServiceServiceImpl} object.
     */
    public static ServiceServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public List<Service> getAllServices() {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            ServiceDao serviceDao = daoFactory.createServiceDao(daoConnection);
            return serviceDao.getAllServices();
        }
    }

    @Override
    public List<Service> getUserServices(long userId) {
        try (DaoConnection daoConnection = daoFactory.getDaoConnection()) {
            ServiceDao serviceDao = daoFactory.createServiceDao(daoConnection);
            return serviceDao.getUserServices(userId);
        }
    }
}
