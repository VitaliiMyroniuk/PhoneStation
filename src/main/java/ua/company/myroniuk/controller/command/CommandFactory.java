package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.controller.command.admin.*;
import ua.company.myroniuk.controller.command.general.*;
import ua.company.myroniuk.controller.command.user.*;

/**
 * The class describes the factory for the creation of {@code Command} objects.
 *
 * @author Vitalii Myroniuk
 */
public class CommandFactory {
    /**
     * The command object.
     */
    private static Command command;

    /**
     * The method creates an appropriate command object depending on the given {@code uri}.
     *
     * @param uri uri based on which the corresponding command is created.
     * @return an appropriate command object.
     */
    public static Command createCommand(String uri) {
        switch (uri) {
            case "/phone_station/account_refill" :
                command = new AccountRefillCommand();
                break;
            case "/phone_station/block_user" :
                command = new BlockUserCommand();
                break;
            case "/phone_station/debtors" :
                command = new DebtorsCommand();
                break;
            case "/phone_station/delete_user" :
                command = new DeleteUserCommand();
                break;
            case "/phone_station/invoices" :
                command = new InvoicesCommand();
                break;
            case "/phone_station/language" :
                command = new LanguageCommand();
                break;
            case "/phone_station/login" :
                command = new LoginCommand();
                break;
            case "/phone_station/logout" :
                command = new LogoutCommand();
                break;
            case "/phone_station/new_users" :
                command = new NewUsersCommand();
                break;
            case "/phone_station/pay_invoice" :
                command = new PayInvoiceCommand();
                break;
            case "/phone_station/profile" :
                command = new ProfileCommand();
                break;
            case "/phone_station/register_user" :
                command = new RegisterUserCommand();
                break;
            case "/phone_station/registration" :
                command = new RegistrationCommand();
                break;
            case "/phone_station/services" :
                command = new ServicesCommand();
                break;
            case "/phone_station/switch_off_service" :
                command = new SwitchOffServiceCommand();
                break;
            case "/phone_station/switch_on_service" :
                command = new SwitchOnServiceCommand();
                break;
            case "/phone_station/successful_registration" :
                command = new SuccessfulRegistration();
                break;
            case "/phone_station/unblock_user" :
                command = new UnblockUserCommand();
                break;
            case "/phone_station/user_invoices" :
                command = new UserInvoicesCommand();
                break;
            case "/phone_station/users" :
                command = new UsersCommand();
                break;
        }
        return command;
    }
}
