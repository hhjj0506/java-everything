package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P10989 {
    public static int[] A;
    public static long res;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bf.readLine());
        A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(bf.readLine());
        }
        bf.close();

        radix_sort(A, 5); // 5 meets the condition for the problem, which states the max size of the number is 10000

        for (int i = 0; i < N; i++) {
            bw.write(A[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void radix_sort(int[] A, int max) {
        int[] output = new int[A.length];
        int num_place = 1;
        int cnt = 0;

        while (cnt != max) { // loop for the max size of the number place
            int[] bucket = new int[10];
            for (int i = 0; i < A.length; i++) {
                // start from the first place
                bucket[(A[i] / num_place) % 10]++;
            }

            for (int i = 1; i < 10; i++) {
                // calculate the index using sum array
                bucket[i] += bucket[i-1];
            }

            for (int i = A.length-1; i >= 0; i--) {
                // start from the current place of number
                output[bucket[(A[i] / num_place % 10)] - 1] = A[i];
                bucket[(A[i] / num_place) % 10]--;
            }

            for (int i = 0; i < A.length; i++) {
                // save current sorted data to move to the next place
                A[i] = output[i];
            }

            num_place = num_place * 10;
            cnt++;
        }
    }
}
