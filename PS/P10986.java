package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long sumArr[] = new long[N];
        long countArr[] = new long[M];
        long ans = 0;

        st = new StringTokenizer(bf.readLine());
        sumArr[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) { // make sum array
            sumArr[i] = sumArr[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) { // check the remainders of all numbers in the sum array
            int remainder = (int)(sumArr[i] % M);
            if (remainder == 0) { // if remainder is 0, add count to answer
                ans++;
            }
            countArr[remainder]++; // check the number of each indexes (how many 0s, 1s, etc.)
        }

        for (int i = 0; i < M; i++) {
            // if there are multiple numbers of the same index, check the cases where two pairs of indexes have the same remainder.
            // ex. index 1, 2, 3 are 0, and 4, 5 are 1. there is 1 case where a pair of indexes have the remainder of 1, and 3 cases where the remainder is 0.
            if (countArr[i] > 1) { 
                ans = ans + (countArr[i] * (countArr[i] - 1) / 2);
            }
        }

        System.out.println(ans);
    }
}
