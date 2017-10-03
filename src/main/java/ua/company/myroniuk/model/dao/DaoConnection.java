package ua.company.myroniuk.model.dao;

/**
 * The interface describes the behavior of the {@code DaoConnection} object.
 *
 * @author Vitalii Myroniuk
 */
public interface DaoConnection extends AutoCloseable {
    /**
     * The method begins transaction.
     */
    void beginTransaction();

    /**
     * The method commits transaction.
     */
    void commitTransaction();

    /**
     * The method rollbacks transaction.
     */
    void rollbackTransaction();

    /**
     * The method closes transaction.
     */
    void close();
}
