package PS;

import java.util.Scanner;

public class P2775 {
    public static void main(String[] args) {
        int t, n, k;
        int[][] d = new int[15][15];
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < 15; i++) {
            d[i][1] = 1;
            d[0][i] = i;
        }

        for (int i = 1; i < 15; i++) {
            for (int j = 2; j < 15; j++) {
                d[i][j] = d[i][j - 1] + d[i - 1][j];
            }
        }

        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            k = sc.nextInt();
            n = sc.nextInt();
            System.out.println(d[k][n]);
        }
    }
}
