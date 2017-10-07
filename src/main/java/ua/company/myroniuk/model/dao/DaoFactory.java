package ua.company.myroniuk.model.dao;

import org.apache.log4j.Logger;

/**
 * {@code DaoFactory} is the abstract class that describes the behavior of the {@code DaoFactory} object.
 *
 * @author Vitalii Myroniuk
 */
public abstract class DaoFactory {
    // Factory class name. It should be change depending on which factory you use.
    private static final String FACTORY_CLASS_NAME = "ua.company.myroniuk.model.dao.impl.jdbc.JdbcDaoFactory";
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);
    private static DaoFactory INSTANCE;

    /**
     * The methods provides creating or getting already created {@code DaoFactory} object.
     *
     * @return {@code DaoFactory} object.
     */
    public static DaoFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (DaoFactory.class) {
                if (INSTANCE == null) {
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

    /**
     * The method retrieves {@code DaoConnection} object from the connection pool.
     *
     * @return {@code DaoConnection} object.
     */
    public abstract DaoConnection getDaoConnection();

    /**
     * The method creates {@code AccountDao} object.
     *
     * @param daoConnection {@code DaoConnection} object.
     * @return created {@code AccountDao} object.
     */
    public abstract AccountDao createAccountDao(DaoConnection daoConnection);

    /**
     * The method creates {@code UserDao} object.
     *
     * @param daoConnection {@code DaoConnection} object.
     * @return created {@code UserDao} object.
     */
    public abstract UserDao createUserDao(DaoConnection daoConnection);

    /**
     * The method creates {@code ServiceDao} object.
     *
     * @param daoConnection {@code DaoConnection} object.
     * @return created {@code ServiceDao} object.
     */
    public abstract ServiceDao createServiceDao(DaoConnection daoConnection);

    /**
     * The method creates {@code InvoiceDao} object.
     *
     * @param daoConnection {@code DaoConnection} object.
     * @return created {@code InvoiceDao} object.
     */
    public abstract InvoiceDao createInvoiceDao(DaoConnection daoConnection);
}
