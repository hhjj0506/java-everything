package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m;
        int[][] distance;
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        distance = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) { // initialize array
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 1000001; // save max value
                }
            }
        }

        for (int i = 0; i < m; i++) { // because it's friend relationship, save both ways
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            distance[s][e] = 1;
            distance[e][s] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int ans = 0;

        for (int i = 1; i <= n; i++) { // find the person with the smallest kevin bacon
            int temp = 0;
            for (int j = 1; j <= n; j++) {
                temp = temp + distance[i][j];
            }
            if (min > temp) {
                min = temp;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}
