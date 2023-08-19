package PS;

import java.util.Scanner;

public class P11047 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        int cnt = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (A[i] <= k) { // if current coin's value is smaller or equal to k (target coint val)
                cnt += (k / A[i]); // add the number of coins used to deduct the amount
                k = k % A[i]; // set k as the remaining value after deducting
            }
        }

        System.out.println(cnt);
    }
}
