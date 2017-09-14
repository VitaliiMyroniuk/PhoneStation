package ua.company.myroniuk.controller.parser;

/**
 * @author Vitalii Myroniuk
 */
public class NameParser implements Parser {
    private static final String NAME_REGEX = "^[A-Za-zЄ-ЯҐа-їґ]{1,10}$";

    @Override
    public boolean validate(String string) {
        return string != null && string.matches(NAME_REGEX);
    }
}
