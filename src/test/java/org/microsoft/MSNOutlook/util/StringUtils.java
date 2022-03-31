package org.microsoft.MSNOutlook.util;

import java.util.Random;

public class StringUtils {

    private static final java.lang.String ALFANUMERICAL_ALL_CAPS = "1234567890ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private static final Random random = new Random();

    public static java.lang.String getRandomString(int stringLength) {
        StringBuilder stringbuilder = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            stringbuilder.append(ALFANUMERICAL_ALL_CAPS).charAt(random.nextInt(ALFANUMERICAL_ALL_CAPS.length()));
        }
        return stringbuilder.toString();
    }

    public static String generateRandomPasswordWithPostFixLength (int postFixLength){
        return getRandomString(postFixLength);
    }
}