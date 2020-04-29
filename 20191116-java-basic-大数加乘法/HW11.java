import java.util.Scanner;

public class HW11 {
    public String multiply2(String n) {
        String r = "";
        int c = 0;

        for (int i = n.length() - 1; i >= 0; i--) {
            int p = (n.charAt(i) - '0') * 2 + c;
            c = p / 10;
            r = p % 10 + r;
        }

        if (c > 0)
            r = c + r;
        return r;
    }


    public String divide2(String n) { //return n/2
        String r = "";
        int b = 0;
        int i = 0;
        if (n.charAt(0) < '2') {
            b = 1;
            i = 1;
        }

        for (; i < n.length(); i++) {
            int p = (n.charAt(i) - '0') + b * 10;
            b = p % 2;
            r += p / 2;
        }

        if (r.length() == 0)
            r = "0";

        return r;
    }

    /**
     * compare
     *
     * @param num1
     * @param num2
     * @return
     */
    public int compare(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (len1 != len2) {
            return len1 > len2 ? 1 : -1;
        }
        for (int i = 0; i < len1; i++) {
            if (num1.charAt(i) > num2.charAt(i)) {
                return 1;
            } else if (num1.charAt(i) < num2.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * add
     *
     * @param num1
     * @param num2
     * @return
     */
    public String add(String num1, String num2) {
        String s = "";
        int len1 = num1.length(), len2 = num2.length();
        int i = len1 - 1, j = len2 - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            carry = sum >= 10 ? 1 : 0;
            sum = sum >= 10 ? sum - 10 : sum;
            s = sum + s;
        }
        if (carry == 1) {
            s = "1" + s;
        }
        return s;
    }

    /**
     * subtract
     *
     * @param num1
     * @param num2
     * @return
     */
    public String subtract(String num1, String num2) {
        String symbol = null;
        int comp = compare(num1, num2);
        if (comp == -1) {
            symbol = "-";
            String t = num1;
            num1 = num2;
            num2 = t;
        } else if (comp == 1) {
            symbol = "";
        } else {
            return "0";
        }
        String s = "";
        int len1 = num1.length(), len2 = num2.length();
        int i = len1 - 1, j = len2 - 1;
        int borrow = 0;
        while (i >= 0 || j >= 0) {
            int diff = borrow;
            if (i >= 0) {
                diff += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                diff -= num2.charAt(j) - '0';
                j--;
            }
            if (diff < 0) {
                borrow = -1;
                diff += 10;
            }
            s = diff + s;
        }
        if (s.charAt(0) == '0') {
            s = s.substring(1);
        }
        return symbol + s;
    }

    /**
     * convert binary string b to an equivalent decimal string.
     *
     * @param b
     * @return
     */
    public String bToD(String b) {
        String s = "0";
        String base = "1";
        for (int i = b.length() - 1, j = 0; i >= 0; i--, j++) {
            if (j > 0) {
                base = multiply2(base);
            }
            if (b.charAt(i) - '0' != 0) {
                s = add(base, s);
            }
        }
        return s;
    }

    /**
     * convert binary string b to an equivalent octal string.
     *
     * @param b
     * @return
     */
    public String bToO(String b) {
        String s = "";
        String t = null;
        int len = b.length();
        int visLen = 0;
        for (int i = 0; i < len / 3; i++) {
            t = b.substring(len - visLen - 3, len - visLen);
            s = bToD(t) + s;
            visLen += 3;
        }
        if (visLen < len) {
            t = b.substring(0, len - visLen);
            s = bToD(t) + s;
        }
        return s;
    }

    /**
     * convert binary string b to an equivalent hexadecimal string.
     *
     * @param b
     * @return
     */
    public String bToH(String b) {
        String s = "";
        String t = null;
        int len = b.length();
        int visLen = 0;
        for (int i = 0; i < len / 4; i++) {
            t = b.substring(len - visLen - 4, len - visLen);
            String tds = bToD(t);
            int td = Integer.parseInt(tds);
            if (td >= 10) {
                switch (td) {
                    case 10:
                        s = "A" + s;
                        break;
                    case 11:
                        s = "B" + s;
                        break;
                    case 12:
                        s = "C" + s;
                        break;
                    case 13:
                        s = "D" + s;
                        break;
                    case 14:
                        s = "E" + s;
                        break;
                    case 15:
                        s = "F" + s;
                        break;
                }
            } else {
                s = tds + s;
            }
            visLen += 4;
        }
        if (visLen < len) {
            t = b.substring(0, len - visLen);
            s = bToD(t) + s;
        }
        return s;
    }

    /**
     * convert decimal string d to an equivalent binary string.
     *
     * @param d
     * @return
     */
    public String dToB(String d) {
        String temp = d;
        String s = "";
        if ("0".equals(d)) {
            return "0";
        }
        while (Integer.parseInt(temp) > 0) {
            s = subtract(temp, multiply2(divide2(temp))) + s;
            temp = divide2(temp);
        }
        return s;
    }

    /**
     * convert octal string o to an equivalent binary string.
     *
     * @param o
     * @return
     */
    public String oToB(String o) {
        String s = "";
        for (int i = 0; i < o.length(); i++) {
            switch (o.charAt(i)) {
                case '0':
                    s = s + "000";
                    break;
                case '1':
                    s = s + "001";
                    break;
                case '2':
                    s = s + "010";
                    break;
                case '3':
                    s = s + "011";
                    break;
                case '4':
                    s = s + "100";
                    break;
                case '5':
                    s = s + "101";
                    break;
                case '6':
                    s = s + "110";
                    break;
                case '7':
                    s = s + "111";
                    break;
            }
        }
        for (int i = 0; i == 0; i++) {
            if (s.charAt(i) == '0') {
                s = s.substring(1);
                i--;
            }
        }
        return s;
    }

    /**
     * convert hexadecimal string h to an equivalent binary string.
     *
     * @param h
     * @return
     */
    public String hToB(String h) {
        String s = "";
        for (int i = 0; i < h.length(); i++) {
            switch (h.charAt(i)) {
                case '0':
                    s = s + "0000";
                    break;
                case '1':
                    s = s + "0001";
                    break;
                case '2':
                    s = s + "0010";
                    break;
                case '3':
                    s = s + "0011";
                    break;
                case '4':
                    s = s + "0100";
                    break;
                case '5':
                    s = s + "0101";
                    break;
                case '6':
                    s = s + "0110";
                    break;
                case '7':
                    s = s + "0111";
                    break;
                case '8':
                    s = s + "1000";
                    break;
                case '9':
                    s = s + "1001";
                    break;
                case 'A':
                    s = s + "1010";
                    break;
                case 'B':
                    s = s + "1011";
                    break;
                case 'C':
                    s = s + "1100";
                    break;
                case 'D':
                    s = s + "1101";
                    break;
                case 'E':
                    s = s + "1110";
                    break;
                case 'F':
                    s = s + "1111";
                    break;
            }
        }
        for (int i = 0; i == 0; i++) {
            if (s.charAt(i) == '0') {
                s = s.substring(1);
                i--;
            }
        }
        return s;
    }

    /**
     * method to convert n given in baseFrom system to an
     *
     * @param baseFrom
     * @param baseTo
     * @param n
     * @return
     */
    public String convert(int baseFrom, int baseTo, String n) {
        String s = null;
        switch (baseFrom) {
            case 2:
                switch (baseTo) {
                    case 2:
                        s = n;
                        break;
                    case 8:
                        s = bToO(n);
                        break;
                    case 10:
                        s = bToD(n);
                        break;
                    case 16:
                        s = bToH(n);
                        break;
                }
                break;
            case 8:
                switch (baseTo) {
                    case 2:
                        s = oToB(n);
                        break;
                    case 8:
                        s = n;
                        break;
                    case 10:
                        s = bToD(oToB(n));
                        break;
                    case 16:
                        s = bToH(oToB(n));
                        break;
                }
                break;
            case 10:
                switch (baseTo) {
                    case 2:
                        s = dToB(n);
                        break;
                    case 8:
                        s = bToO(dToB(n));
                        break;
                    case 10:
                        s = n;
                        break;
                    case 16:
                        s = bToH(dToB(n));
                        break;
                }
                break;
            case 16:
                switch (baseTo) {
                    case 2:
                        s = hToB(n);
                        break;
                    case 8:
                        s = bToO(hToB(n));
                        break;
                    case 10:
                        s = bToD(hToB(n));
                        break;
                    case 16:
                        s = n;
                        break;
                }
                break;
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HW11 hw11 = new HW11();
        while (true) {
            System.out.print("Enter the baseFrom(2, 8, 10, 16): ");
            int baseFrom = sc.nextInt();
            System.out.print("Enter the baseTo(2, 8, 10, 16): ");
            int baseTo = sc.nextInt();
            while (true) {
                System.out.print("Enter the number(quit this menu with entering '#'): ");
                String n = sc.next();
                if ("#".equals(n)) {
                    break;
                }
                System.out.println(String.format("%s(%d) -> %s (%d)", n, baseFrom, hw11.convert(baseFrom, baseTo, n), baseTo));
            }

            System.out.println("Do you want to continue?(Enter 0 or other number, 0 is no)");
            int con = sc.nextByte();
            if (con == 0) {
                break;
            }
        }
    }
}
