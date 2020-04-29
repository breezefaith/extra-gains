public class CeaserUtil {
    public static String encode(String text, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(encrypt(text.charAt(i), key));
        }
        return sb.toString();
    }

    public static String decode(String text, int key) {
        key = 0 - key;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(encrypt(text.charAt(i), key));
        }
        return sb.toString();
    }

    private static char encrypt(char c, int k) {
        if (c >= 'a' && c <= 'z') {
            c += k % 26;
            if (c < 'a') {
                c += 26;
            } else if (c > 'z') {
                c -= 26;
            }
        } else if (c >= 'A' && c <= 'Z') {
            c += k % 26;
            if (c < 'A') {
                c += 26;
            } else if (c > 'Z') {
                c -= 26;
            }
        }
        return c;
    }
}
