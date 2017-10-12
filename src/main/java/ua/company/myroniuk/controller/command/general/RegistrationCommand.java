package ua.company.myroniuk.controller.command.general;

import org.apache.log4j.Logger;
import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.Account;
import ua.company.myroniuk.model.entity.Role;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.exception.LoginExistsException;
import ua.company.myroniuk.model.exception.PhoneNumberExistsException;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for performing registration process.
 *
 * @author Vitalii Myroniuk
 */
public class RegistrationCommand implements Command {
    private static final String NAME_REGEX = "^[A-Za-zА-Яа-яЄІЇєії\\s'-]{1,20}$";
    private static final String MIDDLE_NAME_REGEX = "^[A-Za-zА-Яа-яЄІЇєії\\s'-]{0,20}$";
    private static final String SURNAME_REGEX = "^[A-Za-zА-Яа-яЄІЇєії\\s'-]{1,20}$";
    private static final String PHONE_NUMBER_REGEX = "^\\+[1-9][0-9]{11}$";
    private static final String LOGIN_REGEX = "^[A-Za-zА-Яа-яЄІЇєії0-9._'-]{1,20}$";
    private static final String PASSWORD_REGEX = "^[A-Za-zА-Яа-яІЇЄіїє0-9~!@#$%^&*()-_=+'/|.]{5,20}$";
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);
    private UserService userService;

    public RegistrationCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public RegistrationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid = checkInputData(request);
        if (isValid) {
            User user = createUser(request);
            try {
                userService.addUser(user);
                return "redirect:/phone_station/successful_registration";
            } catch (PhoneNumberExistsException e) {
                request.setAttribute("phone_number_is_valid", false);
                LOGGER.error("Error during registration (phone number already exists): ", e);
            } catch (LoginExistsException e) {
                request.setAttribute("login_is_valid", false);
                LOGGER.error("Error during registration (login already exists): ", e);
            }
            return REGISTRATION_JSP;
        } else {
            return REGISTRATION_JSP;
        }
    }

    private boolean checkInputData(HttpServletRequest request) {
        return checkName(request) &
               checkMiddleName(request) &
               checkSurname(request) &
               checkPhoneNumber(request) &
               checkLogin(request) &
               checkPassword(request) &
               checkConfirmedPassword(request);
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
        if (phoneNumber != null && phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            request.setAttribute("phone_number", phoneNumber);
            request.setAttribute("phone_number_is_valid", true);
            return true;
        } else {
            request.setAttribute("phone_number_is_valid", false);
            return false;
        }
    }

    private boolean checkLogin(HttpServletRequest request) {
        String login = request.getParameter("login");
        if (login != null && login.matches(LOGIN_REGEX)) {
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
