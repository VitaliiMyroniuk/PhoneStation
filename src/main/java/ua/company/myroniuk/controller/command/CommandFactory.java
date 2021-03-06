package ua.company.myroniuk.controller.command;

import ua.company.myroniuk.controller.command.admin.*;
import ua.company.myroniuk.controller.command.general.*;
import ua.company.myroniuk.controller.command.user.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The class describes the factory for the creation of {@code Command} objects.
 *
 * @author Vitalii Myroniuk
 */
public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() {
        commands.put("/phone_station/account_refill", new AccountRefillPageCommand());
        commands.put("/phone_station/account_refill/", new AccountRefillCommand());
        commands.put("/phone_station/add_user", new AddUserCommand());
        commands.put("/phone_station/admin_profile", new AdminProfileCommand());
        commands.put("/phone_station/block_user", new BlockUserCommand());
        commands.put("/phone_station/debtors", new DebtorsCommand());
        commands.put("/phone_station/delete_new_user", new DeleteNewUserCommand());
        commands.put("/phone_station/delete_user", new DeleteUserCommand());
        commands.put("/phone_station/invoices", new InvoicesCommand());
        commands.put("/phone_station/language", new LanguageCommand());
        commands.put("/phone_station/login", new LoginPageCommand());
        commands.put("/phone_station/login/", new LoginCommand());
        commands.put("/phone_station/logout", new LogoutCommand());
        commands.put("/phone_station/new_users", new NewUsersCommand());
        commands.put("/phone_station/pay_invoice", new PayInvoiceCommand());
        commands.put("/phone_station/registration", new RegistrationPageCommand());
        commands.put("/phone_station/registration/", new RegistrationCommand());
        commands.put("/phone_station/services", new ServicesCommand());
        commands.put("/phone_station/switch_off_service", new SwitchOffServiceCommand());
        commands.put("/phone_station/switch_on_service", new SwitchOnServiceCommand());
        commands.put("/phone_station/successful_registration", new SuccessfulRegistrationCommand());
        commands.put("/phone_station/unblock_user", new UnblockUserCommand());
        commands.put("/phone_station/users", new UsersCommand());
        commands.put("/phone_station/user_invoices", new UserInvoicesCommand());
        commands.put("/phone_station/user_profile", new UserProfileCommand());
    }

    /**
     * The class {@code SingletonHolder} is the auxiliary static nested class
     * for the thread safe (Bill Pugh) singleton implementation.
     */
    private static class SingletonHolder {
        private static final CommandFactory INSTANCE = new CommandFactory();
    }

    /**
     * The methods provides creating or getting already created {@code CommandFactory} object.
     *
     * @return {@code CommandFactory} object.
     */
    public static CommandFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * The method returns an appropriate command object depending on the given {@code uri}.
     *
     * @param uri uri based on which the corresponding command is returned.
     * @return an appropriate command object.
     */
    public Command getCommand(String uri) {
        return commands.get(uri);
    }
}
