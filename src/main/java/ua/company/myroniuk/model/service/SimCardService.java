package ua.company.myroniuk.model.service;

/**
 * @author Vitalii Myroniuk
 */
public interface SimCardService {

    /**
     * The method checks if the given sim card number is in the data base.
     * @param number the sim card number entered by the user.
     * @return true if the given sim card number is in the data base; <br>
     *         false otherwise.
     */
    boolean checkNumber(String number);
}
