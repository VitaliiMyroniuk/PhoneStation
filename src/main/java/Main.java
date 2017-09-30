import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vitalii Myroniuk
 */
public class Main {
    public static void main(String[] args) {
//        String SUM_REGEX = "^([1-9][0-9]{0,3}|0)\\.[0-9]{1,2}$";
//        if ("1001.02".matches(SUM_REGEX)) {
//            System.out.println("true");
//        } else {
//            System.out.println("false");
//        }

        String path = getOriginalURI("/WEB-INF/view/jsp/user/invoices.jsp");
        System.out.println(path);
    }

    private static String getOriginalURI(String path) {
        Pattern pattern = Pattern.compile("^.*?/([A-Za-z0-9_]+)\\.jsp$");
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "null";
        }
    }
}
