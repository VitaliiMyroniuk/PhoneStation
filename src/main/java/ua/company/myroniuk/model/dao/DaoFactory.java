package ua.company.myroniuk.model.dao;

import org.apache.log4j.Logger;

/**
 * @author Vitalii Myroniuk
 */
public abstract class DaoFactory {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    /**
     * Factory class name. It should be change if you use other factory.
     */
    private static final String FACTORY_CLASS_NAME = "ua.company.myroniuk.model.dao.impl.jdbc.JdbcDaoFactory";

    /**
     * {@code DaoFactory} instance.
     */
    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance(){
        if(INSTANCE == null){
            synchronized (DaoFactory.class){
                if(INSTANCE == null){
                    try {
                        INSTANCE = (DaoFactory) Class.forName(FACTORY_CLASS_NAME).newInstance();
                    } catch (Exception e) {
                        LOGGER.error("Error in getting the instance of DaoFactory: ", e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return INSTANCE;
    }

    public abstract DaoConnection getDaoConnection();
    public abstract AccountDao createAccountDao();
    public abstract UserDao createUserDao();
    public abstract ServiceDao createServiceDao();
    public abstract InvoiceDao createInvoiceDao();
    public abstract AccountDao createAccountDao(DaoConnection daoConnection);
    public abstract UserDao createUserDao(DaoConnection daoConnection);
    public abstract ServiceDao createServiceDao(DaoConnection daoConnection);
    public abstract InvoiceDao createInvoiceDao(DaoConnection daoConnection);
}
