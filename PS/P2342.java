package PS;

import java.util.Scanner;

public class P2342 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0, s = 1;
        // saves n, l, r in order
        int dp[][][] = new int[100001][5][5];
        // contains how much power is needed when moving the foot
        int mp[][] = { {0, 2, 2, 2, 2}, {2, 1, 3, 4, 3}, {2, 3, 1, 3, 4}, {2, 4, 3, 1, 3}, {2, 3, 4, 3, 1}};

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 100001; k++) {
                    dp[k][i][j] = 100001 * 4;
                }
            }
        }
        dp[0][0][0] = 0;

        while (true) {
            n = sc.nextInt();
            if (n == 0) {
                break;
            }

            for (int i = 0; i < 5; i++) {
                if (n == i) { // two feet cannot be in the same place
                    continue;
                }
                for (int j = 0; j < 5; j++) { // save the smallest power needed for right foot moved
                    dp[s][i][n] = Math.min(dp[s - 1][i][j] + mp[j][n], dp[s][i][n]);
                }
            }

            for (int i = 0; i < 5; i++) {
                if (n == i) { // two feet cannot be in the same place
                    continue;
                }
                for (int j = 0; j < 5; j++) { // save the smallest power needed for left foot moved
                    dp[s][n][i] = Math.min(dp[s - 1][j][i] + mp[j][n], dp[s][n][i]);
                }
            }
            s++;
        }
        s--;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[s][i][j]); // get smallest power when all process is done
            }
        }

        System.out.println(min);
    }
}
