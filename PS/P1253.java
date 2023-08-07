package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int res = 0;
        long A[] = new long[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken()); // take in the numbers into the array
        }
        Arrays.sort(A); // sort the array

        for (int i = 0; i < N; i++) { // O(nlogn)
            long target = A[i];
            int start = 0, end = N-1;

            while (start < end) { // repeat until start and end point merge
                if (A[start] + A[end] == target) { // if the sum of two pointers equal to target number, 
                    if (start != i && end != i) { // check that numbers in two pointers are different
                        res++; // add to result
                        break; // and quit
                    } else if (start == i) { // if start pointer is same as target pointer, move the start pointer
                        start++;
                    } else if (end == i) { // if end pointer is same as target pointer, move the end pointer
                        end--;
                    }
                } else if (A[start] + A[end] < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(res);
    }
}
