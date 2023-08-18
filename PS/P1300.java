package PS;

import java.util.Scanner;

public class P1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        long start = 1;
        long end = k; // endpoint is set to k because kth # cannot be bigger than k, therefore answer will be between 1~kth number 
        long ans = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;

            // count how many numbers are less than mid val
            for (int i = 1; i <= n; i++) {
                cnt += Math.min(mid / i, n);
            }

            if (cnt < k) {
                start = mid + 1;
            } else {
                ans = mid; // save current mid val as answer
                end = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
