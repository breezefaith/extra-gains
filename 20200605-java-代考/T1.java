import java.util.Scanner;

public class T1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt();
            if (min > n) {
                min = n;
            }
            if (max < n) {
                max = n;
            }
        }

        System.out.println("max=" + max);
        System.out.println("min=" + min);
    }
}
