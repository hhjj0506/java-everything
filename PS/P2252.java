package PS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2252 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        int[] degree = new int[n + 1]; // array that saves the enter degree of nodes
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i <= n; i++) {
            A.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            A.get(start).add(end);
            degree[end]++; // save degree data (when node is an endpoint, degree increments)
        }

        for (int i = 1; i <= n; i++) { // topology sort
            if (degree[i] == 0) { // if degree is 0, put the node to queue (set as start point)
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");
            for (int next : A.get(now)) { // for every nodes current node is connected to 
                degree[next]--; // decrement degree of target node (meaning it has been visited once)
                if (degree[next] == 0) { // when target node's degree becomes 0, put it inside queue
                    queue.offer(next);
                }
            }
        }
    }
}
