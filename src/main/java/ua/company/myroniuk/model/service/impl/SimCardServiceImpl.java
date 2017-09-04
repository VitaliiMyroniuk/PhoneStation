package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.SimCardDao;
import ua.company.myroniuk.dao.impl.SimCardDaoImpl;
import ua.company.myroniuk.model.entity.SimCard;
import ua.company.myroniuk.model.service.SimCardService;

/**
 * @author Vitalii Myroniuk
 */
public class SimCardServiceImpl implements SimCardService {

    private SimCardDao simCardDao = SimCardDaoImpl.getInstance();

    private SimCardServiceImpl() {
    }

    private static class SingletonHolder {
        private static final SimCardServiceImpl INSTANCE = new SimCardServiceImpl();
    }

    public static SimCardServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * The method checks if the given sim card number is in the data base.
     * @param number the sim card number entered by the user.
     * @return true if the given sim card number is in the data base; <br>
     *         false otherwise.
     */
    @Override
    public boolean checkNumber(String number) {
        //SimCard simCard = simCardDao.getSimCard(simCardNumber);
        //return simCard != null;
        return false;
    }
}
