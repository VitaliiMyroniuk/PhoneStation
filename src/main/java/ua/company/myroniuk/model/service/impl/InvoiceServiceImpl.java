package ua.company.myroniuk.model.service.impl;

import ua.company.myroniuk.model.dao.InvoiceDao;
import ua.company.myroniuk.model.dao.impl.InvoiceDaoImpl;
import ua.company.myroniuk.model.entity.Invoice;
import ua.company.myroniuk.model.service.InvoiceService;

import java.util.List;

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

    @Override
    public List<Invoice> getInvoices(long userId) {
        return invoiceDao.getInvoices(userId);
    }
}
