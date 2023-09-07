package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] distance = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) { // initialize 2d array that contains the distance between nodes
            for (int j = 1; j <= n; j++) {
                if (i == j) { // if start & end are same, distance is 0
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = 100001;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (distance[start][end] > cost) { // because each value in array is set to more than max
                distance[start][end] = cost;
            }
        }

        // floyd-warshall algorithm - because it uses 3 for loops, the time takes V^3
        // it goes through all possible routes and find fastest routes for all traveling cases
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (distance[i][j] == 100001) {
                    System.out.print("0");
                } else {
                    System.out.print(distance[i][j]);
                }

                if (j != n) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
