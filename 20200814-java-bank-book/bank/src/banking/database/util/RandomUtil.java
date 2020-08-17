package banking.database.util;

import java.util.Random;

public class RandomUtil {

    public static String get11RandomNumber() {
        return getFixLenthString(11);
    }

    public static String getFixLenthString(int strLength) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strLength; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
