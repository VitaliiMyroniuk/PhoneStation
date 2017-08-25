package ua.company.myroniuk.model.service.impl;

/**
 * @author Vitalii Myroniuk
 */
public class ServiceServiceImpl {
    private ServiceServiceImpl() {
    }

    private static class SingletonHolder {
        private static final ServiceServiceImpl INSTANCE = new ServiceServiceImpl();
    }

    public static ServiceServiceImpl getInstance() {
        return ServiceServiceImpl.SingletonHolder.INSTANCE;
    }
}
