package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1517 {
    public static int[] A, tmp;
    public static long res;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        A = new int[N+1];
        tmp = new int[N+1];
        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        res = 0;
        merge_sort(1, N);
        System.out.println(res);
    }

    private static void merge_sort(int start, int end) {
        if (end - start < 1) {
            return ;
        }
        int mid = start + (end - start) / 2; // get the middle position of the group
        
        // sort the arrays using merge sort starting from 1 to N, splitting into smaller groups using recursion
        merge_sort(start, mid); 
        merge_sort(mid + 1, end);

        for (int i = start; i <= end; i++) {
            tmp[i] = A[i];
        }

        int k = start;
        int index1 = start;
        int index2 = mid + 1;

        while (index1 <= mid && index2 <= end) {
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                /* IMPORTANT : when the index2, which is in second group, is lower than index1's value, 
                 * consider that a swap has happened, then add the swap & number of data left in first group
                 * to the result.
                */
                res = res + index2 - k; 
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }

        // putting in data left in the first group
        while (index1 <= mid) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }

        // putting in data left in the second group
        while (index2 <= end) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
