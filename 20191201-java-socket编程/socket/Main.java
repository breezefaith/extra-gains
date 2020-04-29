import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int a, b, n;
        String[] items;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a, b, n(exit if n = 0):");
            a = sc.nextInt();
            b = sc.nextInt();
            n = sc.nextInt();
            if (n < 0) {
                break;
            }

            items = new String[n + 1];
            System.out.print(String.format("(%dx + %dy)^%d = ", a, b, n));
            if (n == 0) {
                System.out.println(1);
            } else {
                for (int i = 0; i < items.length; i++) {
                    items[i] = "";
                    int temp = (int) (Math.pow(a, n - i) * Math.pow(b, i)) * (int) nchoosek(n, i);
                    if (temp == 1) {
                        if (i != 0) {
                            items[i] += "+ ";
                        }
                    } else if (temp == -1) {
                        items[i] += "- ";
                    } else if (temp > 0) {
                        items[i] += "+ " + temp;
                    } else if (temp < 0) {
                        items[i] += temp;
                    }

                    if (n - i != 0) {
                        items[i] += "x";
                        if (n - i != 1) {
                            items[i] += "^" + (n - i);
                        }
                    }

                    if (i != 0) {
                        items[i] += "y";
                        if (i != 1) {
                            items[i] += "^" + i;
                        }
                    }
                }

                for (int i = 0; i < items.length; i++) {
                    System.out.print(items[i] + " ");
                }
                System.out.println();
            }
        }
        ;
    }


    public static long nchoosek(int n, int k) {
        k = (k > (n - k)) ? n - k : k;  // C(n, k) = C(n, n - k)
        if (k <= 1) {  // C(n, 0) = 1, C(n, 1) = n
            return k == 0 ? 1 : n;
        }
        int divisor = 1;
        for (int i = n - k + 1; i <= n; i++) {
            divisor *= i;
        }
        int dividend = 1;
        for (int i = 2; i <= k; i++) {
            dividend *= i;
        }
        return (long) ((double) divisor / dividend);
    }
}
