package PS;

import java.util.Scanner;

public class P2166 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] x = new long[n + 1];
        long[] y = new long[n + 1];
        double res = 0;

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        x[n] = x[0];
        y[n] = y[0];

        for (int i = 0; i < n; i++) {
            res += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
        }

        String ans = String.format("%.1f", Math.abs(res) / 2.0);
        System.out.println(ans);
    }
}