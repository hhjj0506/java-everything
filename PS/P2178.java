package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178 {
    static int[] dx = {0, 1, 0, -1}; // array used for search top, bottom, left, right
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] A;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            String line = st.nextToken();

            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        BFS(0, 0);
        System.out.println(A[n - 1][m - 1]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j}); // make new int arrays as each element of queue
        
        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            visited[i][j] = true;

            for (int k = 0; k < 4; k++) {
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                // check if coordinate is valid
                if (x >= 0 && y >= 0 && x < n && y < m) {
                    // check if the position is available(not 0) and if it's already visited
                    if (A[x][y] != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1; // update depth
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
    }
}
