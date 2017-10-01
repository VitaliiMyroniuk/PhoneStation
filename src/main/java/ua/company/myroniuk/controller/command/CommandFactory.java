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
                command = AccountRefillCommand.getInstance();
                break;
            case "/phone_station/add_user" :
                command = AddUserCommand.getInstance();
                break;
            case "/phone_station/block_user" :
                command = BlockUserCommand.getInstance();
                break;
            case "/phone_station/debtors" :
                command = DebtorsCommand.getInstance();
                break;
            case "/phone_station/delete_user" :
                command = DeleteUserCommand.getInstance();
                break;
            case "/phone_station/invoices" :
                command = InvoicesCommand.getInstance();
                break;
            case "/phone_station/language" :
                command = LanguageCommand.getInstance();
                break;
            case "/phone_station/login" :
                command = LoginCommand.getInstance();
                break;
            case "/phone_station/logout" :
                command = LogoutCommand.getInstance();
                break;
            case "/phone_station/new_users" :
                command = NewUsersCommand.getInstance();
                break;
            case "/phone_station/pay_invoice" :
                command = PayInvoiceCommand.getInstance();
                break;
            case "/phone_station/profile" :
                command = ProfileCommand.getInstance();
                break;
            case "/phone_station/registration" :
                command = RegistrationCommand.getInstance();
                break;
            case "/phone_station/services" :
                command = ServicesCommand.getInstance();
                break;
            case "/phone_station/switch_off_service" :
                command = SwitchOffServiceCommand.getInstance();
                break;
            case "/phone_station/switch_on_service" :
                command = SwitchOnServiceCommand.getInstance();
                break;
            case "/phone_station/successful_registration" :
                command = SuccessfulRegistrationCommand.getInstance();
                break;
            case "/phone_station/unblock_user" :
                command = UnblockUserCommand.getInstance();
                break;
            case "/phone_station/user_invoices" :
                command = UserInvoicesCommand.getInstance();
                break;
            case "/phone_station/users" :
                command = UsersCommand.getInstance();
                break;
        }
        return command;
    }
}
