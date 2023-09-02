package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int m = Integer.parseInt(bf.readLine());
        ArrayList<ArrayList<cityNode>> A = new ArrayList<>();
        ArrayList<ArrayList<cityNode>> reverseA = new ArrayList<>(); // reverse array is needed for finding the longest road by using reverse topology sort
        int[] degree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
            reverseA.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A.get(start).add(new cityNode(end, v));
            reverseA.get(end).add(new cityNode(start, v)); // save reverse array
            degree[end]++;
        }

        StringTokenizer st = new StringTokenizer(bf.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startCity);
        int[] res = new int[n + 1];

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (cityNode next : A.get(now)) {
                degree[next.target]--;
                res[next.target] = Math.max(res[next.target], res[now] + next.val);
                if (degree[next.target] == 0) {
                    queue.offer(next.target);
                }
            }
        }

        // reverse topology sort to find the longest road (the road person should run without resting)
        int cnt = 0;
        boolean[] visited = new boolean[n + 1];
        queue = new LinkedList<>();
        queue.offer(endCity);
        visited[endCity] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (cityNode next : reverseA.get(now)) {
                if (res[next.target] + next.val == res[now]) { // check if road is required to run without resting (if current val equals to prev val + road between val)
                    cnt++;
                    if (visited[next.target] == false) { // to prevent multiple visit
                        visited[next.target] = true;
                        queue.offer(next.target);
                    }
                }
            }
        }

        System.out.println(res[endCity]);
        System.out.println(cnt);
    }
}

class cityNode {
    int target;
    int val;

    cityNode (int target, int val) {
        this.target = target;
        this.val = val;
    }
}
