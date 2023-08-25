package PS;

import java.util.Scanner;

public class P11689 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long res = n;

        // similar to getting prime #, repeat until sqrt of n
        for (long p = 2; p <= Math.sqrt(n); p++) {
            if (n % p == 0) { // check if p is a prime factor
                res = res - res / p; // update the result
                while (n % p == 0) { // remove prime factors from n
                    n /= p;
                }
            }
        }

        if (n > 1) { // if prime factor is still left
            res = res - res / n;
        }

        System.out.println(res);
    }
}
