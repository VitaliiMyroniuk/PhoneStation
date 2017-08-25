package ua.company.myroniuk.model.service.impl;

/**
 * @author Vitalii Myroniuk
 */
public class SimCardServiceImpl {
    private SimCardServiceImpl() {
    }

    private static class SingletonHolder {
        private static final SimCardServiceImpl INSTANCE = new SimCardServiceImpl();
    }

    public static SimCardServiceImpl getInstance() {
        return SimCardServiceImpl.SingletonHolder.INSTANCE;
    }

}
