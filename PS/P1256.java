package PS;

import java.util.Scanner;

public class P1256 {
    public static void main(String[] args) {
        int n, m, k;
        int[][] d = new int[202][202];
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    d[i][j] = 1;
                } else {
                    d[i][j] = d[i - 1][j - 1] + d[i - 1][j];
                    if (d[i][j] > 1000000000) {
                        d[i][j] = 1000000001; // save max num if value is over k range
                    }
                }
            }
        }

        if (d[n + m][m] < k) { // if k can't be made with given numbers
            System.out.println("-1");
        } else {
            while (!(n == 0 && m == 0)) {
                if (d[n - 1 + m][m] >= k) { // when a is chosen and the range of numbers that can be made with characters left is bigger than k
                    System.out.print("a");
                    n--;
                } else { // the range is smaller than k
                    System.out.print("z");
                    k = k - d[n - 1 + m][m]; // update k value
                    m--;
                }
            }
        }
    }
}
