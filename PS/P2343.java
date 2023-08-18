package PS;

import java.util.Scanner;

public class P2343 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] A = new int[n];
        int start = 0;
        int end = 0;

        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
            if (start < A[i]) { // save the max lesson value as the starting index
                start = A[i];
            }
            end = end + A[i]; // save sum of all lessons as end index
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 0;

            for (int i = 0; i < n; i++) { // check if middle value can save all lessons
                if (sum + A[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum = sum + A[i];
            }

            if (sum != 0) { // if sum is not 0 after search, more blueray is needed so add 1
                cnt++;
            }

            if (cnt > m) { // if there are more bluerays needed than target, move the starting point
                start = mid + 1;
            } else { // vice versa
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
