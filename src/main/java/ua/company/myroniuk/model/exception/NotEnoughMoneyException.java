package ua.company.myroniuk.model.exception;

/**
 * The class describes an exception that occurs
 * when the user doesn't have enough money for performing payment.
 *
 * @author Vitalii Myroniuk
 */
public class NotEnoughMoneyException extends Exception {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
