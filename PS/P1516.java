package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1516 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        int[] degree = new int[n + 1];
        int[] selfBuild = new int[n + 1]; // time needed to build itself
        int[] res = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            selfBuild[i] = Integer.parseInt(st.nextToken()); // save time to build building
            while (true) { // save adjacent data to arraylist
                int temp = Integer.parseInt(st.nextToken());
                if (temp == -1) {
                    break;
                }
                A.get(temp).add(i);
                degree[i]++; // save degree data (when node is an endpoint to other node, degree increments)
            }
        }

        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) { // if degree is 0, put the node to queue (set as start point)
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : A.get(now)) {  // for every nodes current node is connected to 
                degree[next]--; // decrement degree of target node (meaning it has been visited once)
                res[next] = Math.max(res[next], res[now] + selfBuild[now]); // update the result array value as the bigger value (current val or current node + build time)
                if (degree[next] == 0) { // when target node's degree becomes 0, put it inside queue
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i<= n; i++) {
            System.out.println(res[i] + selfBuild[i]);
        }
    }
}
