package ua.company.myroniuk.controller.command;

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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // 1) retrieve the data from the form of jsp
        String name = request.getParameter("name");
        String middleName = request.getParameter("middle_name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phone_number");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // 2) check login and phone number (if something is wrong then we return REGISTRATION_JSP with warnings)
        AccountService accountService = AccountServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        // TODO realize warnings in case the input is not valid
        if (accountService.checkLogin(login) || userService.checkPhoneNumber(phoneNumber) || login == null || login.isEmpty()) {
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
