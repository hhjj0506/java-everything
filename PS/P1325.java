package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1325 {
    static int n, m;
    static boolean visited[];
    static int ans[];
    static ArrayList<Integer> A[];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new ArrayList[n + 1];
        ans = new int[n + 1];
        int maxVal = 0;
        
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start].add(end);
        }

        for (int i = 1; i <= n; i++) { // execute BFS in every node to find the most trusted computer
            visited = new boolean[n + 1];
            BFS(i);
        }

        for (int i = 1; i<= n; i++) { // find the max value (the most trusted computer) in the ans array
            maxVal = Math.max(maxVal, ans[i]);
        }

        for (int i = 1; i<= n; i++) { // print out the node with maxVal
            if (ans[i] == maxVal) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS (int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int i : A[now]) {
                if (visited[i] == false) {
                    visited[i] = true;
                    ans[i]++; // increment the value of the node in ans array
                    queue.add(i);
                }
            }
        }
    }
}
