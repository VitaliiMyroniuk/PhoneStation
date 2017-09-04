package ua.company.myroniuk.dao.impl;

import ua.company.myroniuk.dao.InvoiceDao;

/**
 * @author Vitalii Myroniuk
 */
public class InvoiceDaoImpl implements InvoiceDao {
    private InvoiceDaoImpl() {
    }

    private static class SingletonHolder {
        private static final InvoiceDaoImpl INSTANCE = new InvoiceDaoImpl();
    }

    public static InvoiceDaoImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
