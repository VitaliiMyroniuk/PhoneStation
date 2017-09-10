//package ua.company.myroniuk.model.service.impl.rubbish;
//
//import ua.company.myroniuk.dao.impl.rubbish.SimCardDaoImpl;
//
///**
// * @author Vitalii Myroniuk
// */
//public class SimCardServiceImpl implements SimCardService {
//
//    private SimCardDao simCardDao = SimCardDaoImpl.getInstance();
//
//    private SimCardServiceImpl() {
//    }
//
//    private static class SingletonHolder {
//        private static final SimCardServiceImpl INSTANCE = new SimCardServiceImpl();
//    }
//
//    public static SimCardServiceImpl getInstance() {
//        return SingletonHolder.INSTANCE;
//    }
//
//    /**
//     * The method checks if the given sim card number is in the data base.
//     * @param number the sim card number entered by the user.
//     * @return true if the given sim card number is in the data base; <br>
//     *         false otherwise.
//     */
//    @Override
//    public boolean checkNumber(String number) {
//        SimCard simCard = simCardDao.getSimCard(number);
//        return simCard != null;
//    }
//}
