package ua.company.myroniuk.model.service;

import ua.company.myroniuk.model.entity.Invoice;

import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public interface InvoiceService {

    List<Invoice> getInvoices(long userId);

}
