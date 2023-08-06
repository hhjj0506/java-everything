package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int start = 1, end = 1, count = 1, sum = 1;

        while (end != N) {
            if (sum == N) { // if sum from start to end equals to N, add to count and start from new endpoint (where sum will be bigger than N, so can restart)
                count++;
                end++;
                sum = sum + end;
            } else if (sum > N) { // if N is less than sum, start from 1 place higher
                sum = sum - start;
                start++;
            } else { // if N is bigger than sum (which will be the case for most time), move the endpoint 1 place higher
                end++;
                sum = sum + end;
            }
        }

        System.out.println(count);
    }
}
