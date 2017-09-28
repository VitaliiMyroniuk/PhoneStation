package ua.company.myroniuk.model.dao;

/**
 * @author Vitalii Myroniuk
 */
public interface DaoConnection extends AutoCloseable {
    void beginTransaction();
    void commitTransaction();
    void rollbackTransaction();
    void close();
}
