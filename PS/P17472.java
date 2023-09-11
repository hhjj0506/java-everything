package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17472 {
    static int[] dr = {-1, 0, 1, 0}; // used to search 4 directions
    static int[] dc = {0, 1, 0, -1}; // same
    static int[] parent;
    static int[][] map;
    static int n, m, num;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumList; // save all islandds
    static ArrayList<int[]> mList; // saves one island
    static PriorityQueue<bEdge> queue; // save bridge info
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        num = 1;
        sumList = new ArrayList<>();
        queue = new PriorityQueue<>();
        int useEdge = 0;
        int res = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // save map info
            }
        }

        // perform BFS from every node that is labeled as land. search the surrounding and if another land is found right next to it, label it as the same land.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && visited[i][j] != true) {
                    BFS(i, j);
                    num++;
                    sumList.add(mList);
                }
            }
        }

        // find all bridges possible from the island and save it to queue
        for (int i = 0; i < sumList.size(); i++) {
            ArrayList<int[]> now = sumList.get(i); // info of one island
            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0];
                int c = now.get(j)[1];
                int nowS = map[r][c];

                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d];
                    int tempC = dc[d];
                    int bLength = 0;
                    while (r + tempR >= 0 && r + tempR < n && c + tempC >= 0 && c + tempC < m) {
                        if (map[r + tempR][c + tempC] == nowS) { // can't build bridge if the land is same island's
                            break;
                        } else if (map[r + tempR][c + tempC] != 0) { // if land is not the same island's nor ocean
                            if (bLength > 1) { // only save when bridge length is more than 1
                                queue.add(new bEdge(nowS, map[r + tempR][c + tempC], bLength));
                            } 
                            break;
                        } else {
                            bLength++;
                        }

                        if (tempR < 0) {
                            tempR--;
                        } else if (tempR > 0) {
                            tempR++;
                        } else if (tempC < 0) {
                            tempC--;
                        } else if (tempC > 0) {
                            tempC++;
                        }
                    }
                }
            }
        }

        parent = new int[num];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        while (!queue.isEmpty()) {
            bEdge now = queue.poll();
            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                res = res + now.v;
                useEdge++;
            }
        }

        if (useEdge == num - 2) {
            System.out.println(res);
        } else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) { // connect representative nodes
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if (a == parent[a]) { 
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        mList = new ArrayList<>();
        int[] start = {i, j};
        queue.add(start);
        mList.add(start);
        visited[i][j] = true;
        map[i][j] = num;

        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            int r = now[0];
            int c = now[1];
            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];
                while (r + tempR >= 0 && r + tempR < n && c + tempC >= 0 && c + tempC < m) {
                    // if land is not visited nor water, consider it as land of same island
                    if (visited[r + tempR][c + tempC] == false && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, queue);
                    } else {
                        break;
                    }

                    if (tempR < 0) {
                        tempR--;
                    } else if (tempR > 0) {
                        tempR++;
                    } else if (tempC < 0) {
                        tempC--;
                    } else if (tempC > 0) {
                        tempC++;
                    }
                }
            }
        }
    }

    // used to add island info
    private static void addNode(int i, int j, Queue<int[]> queue) {
        map[i][j] = num;
        visited[i][j] = true;
        int[] temp = {i, j};
        mList.add(temp);
        queue.add(temp);
    }
}

class bEdge implements Comparable<bEdge> {
    int s, e, v;
    bEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    @Override
    public int compareTo(bEdge o) {
        return this.v - o.v;
    }
}
