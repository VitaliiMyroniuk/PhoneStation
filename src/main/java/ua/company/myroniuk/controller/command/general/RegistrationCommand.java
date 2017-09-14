package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.controller.parser.Parser;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.AccountService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.AccountServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vitalii Myroniuk
 */
public class RegistrationCommand implements Command {
    private final String NAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{1,10}$";
    private final String MIDDLE_NAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{0,10}$";
    private final String SURNAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{1,10}$";
    private final String PHONE_NUMBER_REGEX = "^\\+[1-9]{1}[0-9]{11}$";
    private final String LOGIN_REGEX = "^[A-Za-zЄ-Яа-ї0-9._-]{1,10}$";
    private final String PASSWORD_REGEX = "^[A-Za-z0-9~!@#$%^&*()-_=+/|.]{5,15}$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // 1) retrieve the data from the form of jsp
        String name = request.getParameter("name");
        String middleName = request.getParameter("middle_name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmed_password");

        // 2) validate retrieved data (if something is wrong then we return REGISTRATION_JSP with warnings)
        // TODO realize warnings in case the input is not valid
        boolean isValid =
                name != null &&
                middleName != null &&
                surname != null &&
                phoneNumber != null &&
                login != null &&
                password != null &&
                name.matches(NAME_REGEX) &&
                middleName.matches(MIDDLE_NAME_REGEX) &&
                surname.matches(SURNAME_REGEX) &&
                phoneNumber.matches(PHONE_NUMBER_REGEX) &&
                login.matches(LOGIN_REGEX) &&
                password.matches(PASSWORD_REGEX) &&
                password.equals(confirmedPassword);
        if (!isValid) {
            return REGISTRATION_JSP;
        }

        // 3) check login and phone number (if something is wrong then we return REGISTRATION_JSP with warnings)
        AccountService accountService = AccountServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        if (accountService.checkLogin(login) || userService.checkPhoneNumber(phoneNumber)) {
            return REGISTRATION_JSP;
        }

        // 4) create account
        Account account = new Account();
        account.setLogin(login);
        account.setPassword(password);
        account.setAdmin(false);

        // 5) create user
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setMiddleName(middleName);
        user.setSurname(surname);
        user.setPhoneNumber(phoneNumber);
        user.setBalance(0);
        user.setRegistered(false);
        user.setBlocked(false);

        // 6) add the user into the data base
        userService.addUser(user);
        return SUCCESSFUL_REGISTRATION_JSP;
    }
}
