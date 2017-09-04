package ua.company.myroniuk.controller.command;

/**
 * @author Vitalii Myroniuk
 */
public class CommandFactory {
    private static Command command;

    public static Command createCommand(String query) {
        switch (query) {
            case "login" :
                command = new LoginCommand();
                break;
            case "logout" :
                command = new LogoutCommand();
                break;
            case "registration" :
                command = new RegistrationCommand();
                break;
            default:
                command = null;
                break;
        }
        return command;
    }
}
