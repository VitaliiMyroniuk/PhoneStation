package ua.company.myroniuk.controller.command.general;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Role;
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

    private final String NAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{1,15}$";

    private final String MIDDLE_NAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{0,15}$";

    private final String SURNAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{1,15}$";

    private final String PHONE_NUMBER_REGEX = "^\\+[1-9]{1}[0-9]{11}$";

    private final String LOGIN_REGEX = "^[A-Za-zЄ-Яа-ї0-9._-]{1,15}$";

    private final String PASSWORD_REGEX = "^[A-Za-zЄ-Яа-ї0-9~!@#$%^&*()-_=+/|.]{5,15}$";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid =
                checkName(request) &
                checkMiddleName(request) &
                checkSurname(request) &
                checkPhoneNumber(request) &
                checkLogin(request) &
                checkPassword(request) &
                checkConfirmedPassword(request);
        if (isValid) {
            UserService userService = UserServiceImpl.getInstance();
            User user = createUser(request);
            userService.addUser(user);
            return "redirect:/phone_station/successful_registration";
        } else {
            return REGISTRATION_JSP;
        }
    }

    private boolean checkName(HttpServletRequest request) {
        String name = request.getParameter("name");
        if (name != null && name.matches(NAME_REGEX)) {
            request.setAttribute("name", name);
            request.setAttribute("name_is_valid", true);
            return true;
        } else {
            request.setAttribute("name_is_valid", false);
            return false;
        }
    }

    private boolean checkMiddleName(HttpServletRequest request) {
        String middleName = request.getParameter("middle_name");
        if (middleName == null || middleName.matches(MIDDLE_NAME_REGEX)) {
            request.setAttribute("middle_name", middleName);
            request.setAttribute("middle_name_is_valid", true);
            return true;
        } else {
            request.setAttribute("middle_name_is_valid", false);
            return false;
        }
    }

    private boolean checkSurname(HttpServletRequest request) {
        String surname = request.getParameter("surname");
        if (surname != null && surname.matches(SURNAME_REGEX)) {
            request.setAttribute("surname", surname);
            request.setAttribute("surname_is_valid", true);
            return true;
        } else {
            request.setAttribute("surname_is_valid", false);
            return false;
        }
    }

    private boolean checkPhoneNumber(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phone_number");
        UserService userService = UserServiceImpl.getInstance();
        if (phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_REGEX) &&
                                   !userService.checkPhoneNumber(phoneNumber)) {
            request.setAttribute("phone_number", phoneNumber);
            request.setAttribute("phone_number_is_valid", true);
            return true;
        } else {
            request.setAttribute("phone_number_is_valid", false);
            return false;
        }
    }

    private boolean checkLogin(HttpServletRequest request) {
        AccountService accountService = AccountServiceImpl.getInstance();
        String login = request.getParameter("login");
        if (login != null && login.matches(LOGIN_REGEX) &&
                             !accountService.checkLogin(login)) {
            request.setAttribute("login", login);
            request.setAttribute("login_is_valid", true);
            return true;
        } else {
            request.setAttribute("login_is_valid", false);
            return false;
        }
    }

    private boolean checkPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        if (password != null && password.matches(PASSWORD_REGEX)) {
            request.setAttribute("password", password);
            request.setAttribute("password_is_valid", true);
            return true;
        } else {
            request.setAttribute("password_is_valid", false);
            return false;
        }
    }

    private boolean checkConfirmedPassword(HttpServletRequest request) {
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmed_password");
        if (password != null && password.matches(PASSWORD_REGEX) && password.equals(confirmedPassword)) {
            request.setAttribute("confirmed_password", confirmedPassword);
            request.setAttribute("confirmed_password_is_valid", true);
            return true;
        } else {
            request.setAttribute("confirmed_password_is_valid", false);
            return false;
        }
    }

    private Account createAccount(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        return new Account.Builder()
                .setLogin(login)
                .setPassword(password)
                .setRole(Role.SUBSCRIBER)
                .build();
    }

    private User createUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String middleName = request.getParameter("middle_name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        Account account = createAccount(request);
        return new User.Builder()
                .setAccount(account)
                .setName(name)
                .setMiddleName(middleName)
                .setSurname(surname)
                .setPhoneNumber(phoneNumber)
                .setBalance(0)
                .setRegistered(false)
                .setBlocked(false)
                .build();
    }
}
