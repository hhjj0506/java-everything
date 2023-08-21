package PS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class P1913 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][2];
        int cnt = 0;
        int end = -1;

        for (int i = 0; i < n; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }

        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] start, int[] end) {
                if (start[1] == end[1]) { // if end times are equal
                    return start[0] - end[0];
                }

                return start[1] - end[1];
            }
        });

        for (int i = 0; i < n; i++) {
            if (A[i][0] >= end) { // when another meeting that has later end time exists
                end = A[i][1]; // update the end time
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
