package utils;

import pages.CommonMethods;

public class Token {

    public static final String AdminToken = CommonMethods.getToken("STARK_A");
    public static final String PartToken = CommonMethods.getToken("STARK_P");

    public static String getTokenByUser(String user) {
        if (user.equals("STARK_A")){
            return AdminToken;
        } else {
            return PartToken;
        }
    }
}
