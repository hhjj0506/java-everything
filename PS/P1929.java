package PS;

import java.util.Scanner;

public class P1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] A = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) { // repeat only until sqrt of n
            if (A[i] == 0) { // continue if num is not prime number
                continue;
            }
            for (int j = i + i; j <= n; j = j + i) { // delete numbers that are multiple of prime numbers
                A[j] = 0;
            }
        }

        for (int i = m; i <= n; i++) {
            if (A[i] != 0) { // print out all prime numbers until n
                System.out.println(A[(int)i]);
            }
        }
    }
}
