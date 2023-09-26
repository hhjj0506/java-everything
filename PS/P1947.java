package PS;

import java.util.Scanner;

public class P1947 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long mod = 1000000000;
        long d[] = new long[1000001];
        d[1] = 0; // if there's one person, the gift swap cannot happen
        d[2] = 1; // if there are two people, they swap their gifts

        for (int i = 3; i <= n; i++) {
            d[i] = (i - 1) *(d[i - 1] + d[i - 2]) % mod;
        }

        System.out.println(d[n]);
    }
}
