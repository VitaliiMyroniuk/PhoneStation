package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.controller.command.admin.AllUsersCommand;
import ua.company.myroniuk.controller.command.admin.DebtorsCommand;
import ua.company.myroniuk.controller.command.admin.NewUsersCommand;
import ua.company.myroniuk.controller.command.general.*;
import ua.company.myroniuk.controller.command.user.AccountRefillCommand;
import ua.company.myroniuk.controller.command.user.InvoicesCommand;
import ua.company.myroniuk.controller.command.user.ServicesCommand;

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
            case "all_users" :
                command = new AllUsersCommand();
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
            case "profile" :
                command = new ProfileCommand();
                break;
            case "registration" :
                command = new RegistrationCommand();
                break;
            case "services" :
                command = new ServicesCommand();
                break;
            default:
                command = new UnknownCommand();
                break;
        }
        return command;
    }
}
