package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.DaoFactory;
import ua.company.myroniuk.model.dao.InvoiceDao;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.service.InvoiceService;

import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class InvoiceServiceImpl implements InvoiceService {
    /**
     * DaoFactory object.
     */
    private DaoFactory daoFactory;

    /**
     * Constructor for creating empty {@code InvoiceServiceImpl} object.
     */
    private InvoiceServiceImpl() {
        this.daoFactory = DaoFactory.getInstance();
    }

    /**
     * The class {@code SingletonHolder} is the auxiliary static nested class
     * for the thread safe (Bill Pugh) singleton implementation.
     */
    private static class SingletonHolder {
        private static final InvoiceServiceImpl INSTANCE = new InvoiceServiceImpl();
    }

    /**
     * The methods provides creating or getting already created {@code InvoiceServiceImpl} object.
     *
     * @return {@code InvoiceServiceImpl} object.
     */
    public static InvoiceServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public List<Invoice> getInvoices(long userId) {
        InvoiceDao invoiceDao = daoFactory.createInvoiceDao();
        return invoiceDao.getInvoices(userId);
    }
}
