package ua.company.myroniuk.model.exception;

/**
 * The class describes an exception that occurs
 * when the user tries to register new account and entered phone number already exists.
 *
 * @author Vitalii Myroniuk
 */
public class PhoneNumberExistsException extends Exception {
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
