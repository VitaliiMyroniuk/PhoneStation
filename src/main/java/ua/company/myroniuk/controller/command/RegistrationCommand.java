package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.model.entity.Service;
import ua.company.myroniuk.model.entity.SimCard;
import ua.company.myroniuk.model.entity.User;
import ua.company.myroniuk.model.service.SimCardService;
import ua.company.myroniuk.model.service.UserService;
import ua.company.myroniuk.model.service.impl.SimCardServiceImpl;
import ua.company.myroniuk.model.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitalii Myroniuk
 */
public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // 1) retrieve the data from the form of jsp
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String passport = request.getParameter("passport");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // 2) check login and sim card number (if something is wrong then we return REGISTRATION_JSP with warnings)
        UserService userService = UserServiceImpl.getInstance();
        SimCardService simCardService = SimCardServiceImpl.getInstance();
        // TODO check sim card number and realize warnings
        if (userService.checkLogin(login)) {
            return REGISTRATION_JSP;
        }

        // 3) create standard service
        List<Service> services = new ArrayList<>();
        Service service = new Service();
        service.setName("Standard");
        service.setPrice(0);
        service.setStartDate(LocalDate.now());
        service.setEndDate(LocalDate.now());
        service.setActive(true);
        services.add(service);

        // 4) create sim-card
        SimCard simCard = new SimCard();
        simCard.setNumber(number);
        simCard.setServices(services);
        simCard.setBalance(1000);
        simCard.setRegistered(false);
        simCard.setBlocked(false);

        // 5) create user
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassport(passport);
        user.setSimCard(simCard);
        user.setLogin(login);
        user.setPassword(password);
        user.setAdmin(false);

        // 6) add the user into the data base
        userService.addUser(user);

        return SUCCESSFUL_REGISTRATION_JSP;
    }
}
