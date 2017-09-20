package ua.company.myroniuk.model.service;

/**
 * The {@code AccountService} interface provides methods for {@code Account} object.
 *
 * @author Vitalii Myroniuk
 */
public interface AccountService {

    /**
     * The method checks the given login and password.
     * @param login the login entered by the user.
     * @param password the password entered by the user.
     * @return 1 if the given login and password correspond with admin; <br>
     *         0 if the given login and password correspond with user; <br>
     *         -1 if there is no such login and password in the data base.
     */
    int checkLoginAndPassword(String login, String password);

    /**
     * The method checks if the given login is in the data base.
     * @param login the login entered by the user.
     * @return true if the given login is in the data base; <br>
     *         false otherwise.
     */
    boolean checkLogin(String login);
}
