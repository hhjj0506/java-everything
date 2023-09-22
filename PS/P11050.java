package PS;

import java.util.Scanner;

public class P11050 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] d = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            d[i][1] = i;
            d[i][0] = 1;
            d[i][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                d[i][j] = d[i - 1][j] + d[i - 1][j - 1]; // basic combination recurrence relation
            }
        }

        System.out.println(d[n][k]);
    }
}
