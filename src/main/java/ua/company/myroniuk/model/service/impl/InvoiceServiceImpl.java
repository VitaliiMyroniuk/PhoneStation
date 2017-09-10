package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.dao.InvoiceDao;
import ua.company.myroniuk.dao.impl.InvoiceDaoImpl;
import ua.company.myroniuk.model.service.InvoiceService;

/**
 * @author Vitalii Myroniuk
 */
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceDao invoiceDao = InvoiceDaoImpl.getInstance();

    private InvoiceServiceImpl() {
    }

    private static class SingletonHolder {
        private static final InvoiceServiceImpl INSTANCE = new InvoiceServiceImpl();
    }

    public static InvoiceServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }
}