package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.ServiceDao;
import ua.company.myroniuk.model.dao.impl.ServiceDaoImpl;
import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.service.ServiceService;

import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceServiceImpl implements ServiceService {

    private ServiceDao serviceDao = ServiceDaoImpl.getInstance();

    private ServiceServiceImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceServiceImpl INSTANCE = new ServiceServiceImpl();
    }

    public static ServiceServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public List<Service> getAllServices() {
        return serviceDao.getAllServices();
    }

    @Override
    public List<Service> getUserServices(long userId) {
        return serviceDao.getUserServices(userId);
    }
}
