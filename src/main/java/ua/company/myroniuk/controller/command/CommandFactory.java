package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.controller.command.admin.*;
import ua.company.myroniuk.controller.command.general.*;
import ua.company.myroniuk.controller.command.user.*;
import java.util.HashMap;

/**
 * The class describes the factory for the creation of {@code Command} objects.
 *
 * @author Vitalii Myroniuk
 */
public class CommandFactory {
    /**
     * Hash map of all available commands.
     */
    private static HashMap<String, Command> commands = new HashMap<>();

    static {
        commands.put("/phone_station/account_refill", AccountRefillCommand.getInstance());
        commands.put("/phone_station/add_user", AddUserCommand.getInstance());
        commands.put("/phone_station/block_user", BlockUserCommand.getInstance());
        commands.put("/phone_station/debtors", DebtorsCommand.getInstance());
        commands.put("/phone_station/delete_user", DeleteUserCommand.getInstance());
        commands.put("/phone_station/invoices", InvoicesCommand.getInstance());
        commands.put("/phone_station/language", LanguageCommand.getInstance());
        commands.put("/phone_station/login", LoginCommand.getInstance());
        commands.put("/phone_station/logout", LogoutCommand.getInstance());
        commands.put("/phone_station/new_users", NewUsersCommand.getInstance());
        commands.put("/phone_station/pay_invoice", PayInvoiceCommand.getInstance());
        commands.put("/phone_station/profile", ProfileCommand.getInstance());
        commands.put("/phone_station/registration", RegistrationCommand.getInstance());
        commands.put("/phone_station/services", ServicesCommand.getInstance());
        commands.put("/phone_station/switch_off_service", SwitchOffServiceCommand.getInstance());
        commands.put("/phone_station/switch_on_service", SwitchOnServiceCommand.getInstance());
        commands.put("/phone_station/successful_registration", SuccessfulRegistrationCommand.getInstance());
        commands.put("/phone_station/unblock_user", UnblockUserCommand.getInstance());
        commands.put("/phone_station/user_invoices", UserInvoicesCommand.getInstance());
        commands.put("/phone_station/users", UsersCommand.getInstance());
    }

    /**
     * The method returns an appropriate command object depending on the given {@code uri}.
     *
     * @param uri uri based on which the corresponding command is returned.
     * @return an appropriate command object.
     */
    public static Command getCommand(String uri) {
        return commands.get(uri);
    }
}
