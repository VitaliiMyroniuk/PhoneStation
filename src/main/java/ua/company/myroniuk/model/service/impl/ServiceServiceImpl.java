package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.ServiceDao;
import ua.company.myroniuk.dao.impl.ServiceDaoImpl;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceServiceImpl {

    private ServiceDao serviceDao = ServiceDaoImpl.getInstance();

    private ServiceServiceImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceServiceImpl INSTANCE = new ServiceServiceImpl();
    }

    public static ServiceServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
