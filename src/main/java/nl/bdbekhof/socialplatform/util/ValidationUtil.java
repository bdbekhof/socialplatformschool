package nl.bdbekhof.socialplatform.util;

public class ValidationUtil {
    public static boolean validUsername(String username) {
        if (username == null) return false;
        return username.length() >= 3 && username.matches("[A-Za-z0-9_\\-]+");
    }
}
