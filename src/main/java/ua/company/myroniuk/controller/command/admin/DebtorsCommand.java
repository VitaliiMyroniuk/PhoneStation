package ua.company.myroniuk.controller.command.admin;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for getting all debtors.
 *
 * @author Vitalii Myroniuk
 */
public class DebtorsCommand implements Command {

    private UserService userService;

    private DebtorsCommand(UserService userService) {
        this.userService = userService;
    }

    private static class SingletonHolder {
        private static final DebtorsCommand INSTANCE = new DebtorsCommand(UserServiceImpl.getInstance());
    }

    public static DebtorsCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getDebtors();
        request.setAttribute("debtors", users);
        return DEBTORS_JSP;
    }
}
