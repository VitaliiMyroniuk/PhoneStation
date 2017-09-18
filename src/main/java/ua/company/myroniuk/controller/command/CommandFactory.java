package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.controller.command.admin.*;
import ua.company.myroniuk.controller.command.general.*;
import ua.company.myroniuk.controller.command.user.*;

/**
 * @author Vitalii Myroniuk
 */
public class CommandFactory {

    private static Command command;

    public static Command createCommand(String query) {
        //TODO find out if the commands should be singletons
        switch (query) {
            case "account_refill" :
                command = new AccountRefillCommand();
                break;
            case "block_user" :
                command = new BlockUserCommand();
                break;
            case "debtors" :
                command = new DebtorsCommand();
                break;
            case "invoices" :
                command = new InvoicesCommand();
                break;
            case "language" :
                command = new LanguageCommand();
                break;
            case "login" :
                command = new LoginCommand();
                break;
            case "logout" :
                command = new LogoutCommand();
                break;
            case "new_users" :
                command = new NewUsersCommand();
                break;
            case "pay_invoice" :
                command = new PayInvoiceCommand();
                break;
            case "profile" :
                command = new ProfileCommand();
                break;
            case "register_user" :
                command = new RegisterUserCommand();
                break;
            case "registration" :
                command = new RegistrationCommand();
                break;
            case "services" :
                command = new ServicesCommand();
                break;
            case "switch_off_service" :
                command = new SwitchOffServiceCommand();
                break;
            case "switch_on_service" :
                command = new SwitchOnServiceCommand();
                break;
            case "unblock_user" :
                command = new UnblockUserCommand();
                break;
            case "user_invoices" :
                command = new UserInvoicesCommand();
                break;
            case "users" :
                command = new UsersCommand();
                break;
            default:
                command = new UnknownCommand();
                break;
        }
        return command;
    }
}
