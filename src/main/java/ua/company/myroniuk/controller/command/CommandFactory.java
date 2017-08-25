package ua.company.myroniuk.controller.command;

/**
 * @author Vitalii Myroniuk
 */
public class CommandFactory {
    private static Command command;

    public static Command createCommand(String query) {
        switch (query) {
            case "authentication" :
                command = new AuthenticationCommand();
                break;
            case "registration" :
                command = new RegistrationCommand();
                break;
            default:
                break;
        }
        return command;
    }
}
