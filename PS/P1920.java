package PS;

import java.util.Arrays;
import java.util.Scanner;

public class P1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];

        // save data into array
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        // sort the array
        Arrays.sort(A);
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            boolean find = false;
            int target = sc.nextInt();
            int start = 0;
            int end = A.length - 1;

            while (start <= end) {
                int midIndex = (start + end) / 2;
                int midVal = A[midIndex];

                // if mid value is bigger than target, reduce end point
                if (midVal > target) {
                    end = midIndex - 1;
                } else if (midVal < target) { // if mid value is smaller than target, add the starting point
                    start = midIndex + 1;
                } else { // when target is the same as mid value, target is found
                    find = true;
                    break;
                }
            }
            
            if (find) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
