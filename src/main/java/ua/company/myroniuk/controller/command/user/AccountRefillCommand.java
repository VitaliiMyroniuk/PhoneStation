package ua.company.myroniuk.controller.command.user;

import ua.company.myroniuk.controller.command.Command;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains the method for the account refill process.
 *
 * @author Vitalii Myroniuk
 */
public class AccountRefillCommand implements Command {
    private static final String CREDIT_CARD_NUMBER_REGEX = "^[0-9]{16}$";
    private static final String CVV_REGEX = "^[0-9]{3}$";
    private static final String SUM_REGEX = "^([1-9][0-9]{0,3}|0)(\\.[0-9]{1,2})?$";
    private UserService userService;

    public AccountRefillCommand() {
        this.userService = UserServiceImpl.getInstance();
    }

    public AccountRefillCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid =
                checkCreditCardNumber(request) &
                checkCVV(request) &
                checkSum(request);
        if (isValid) {
            User user = (User) request.getSession().getAttribute("user");
            long userId = user.getId();
            int sum = getSum(request);
            userService.updateBalance(userId, sum);
            return REDIRECT_TO_USER_PROFILE_JSP;
        } else {
            return ACCOUNT_REFILL_JSP;
        }
    }

    private boolean checkCreditCardNumber(HttpServletRequest request) {
        String creditCardNumber = request.getParameter("credit_card_number");
        if (creditCardNumber != null && creditCardNumber.matches(CREDIT_CARD_NUMBER_REGEX)) {
            request.setAttribute("credit_card_number", creditCardNumber);
            request.setAttribute("credit_card_number_is_valid", true);
            return true;
        } else {
            request.setAttribute("credit_card_number_is_valid", false);
            return false;
        }
    }

    private boolean checkCVV(HttpServletRequest request) {
        String cvv = request.getParameter("cvv");
        if (cvv != null && cvv.matches(CVV_REGEX)) {
            request.setAttribute("cvv", cvv);
            request.setAttribute("cvv_is_valid", true);
            return true;
        } else {
            request.setAttribute("cvv_is_valid", false);
            return false;
        }
    }

    private boolean checkSum(HttpServletRequest request) {
        String sum = request.getParameter("sum");
        if (sum != null && sum.matches(SUM_REGEX) && getSum(request) > 0) {
            request.setAttribute("sum", sum);
            request.setAttribute("sum_is_valid", true);
            return true;
        } else {
            request.setAttribute("sum_is_valid", false);
            return false;
        }
    }

    private int getSum(HttpServletRequest request) {
        String stringSum = request.getParameter("sum");
        double sum = Double.parseDouble(stringSum);
        return (int) (100 * sum);
    }
}
