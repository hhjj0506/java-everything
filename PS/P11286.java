package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P11286 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        // priority queue used with set sort condition
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            int first = Math.abs(o1);
            int second = Math.abs(o2);

            if (first == second) { // if abs values are the same, sort prioritizing negative values
                return o1 > o2 ? 1 : -1;
            } else { // if not, sort by absolute value
                return first - second;
            }
        });

        for (int i = 0; i < N; i++) {
            int req = Integer.parseInt(bf.readLine());
            if (req == 0) {
                if (pq.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(pq.poll());
                }
            } else {
                pq.add(req);
            }
        }
    }
}
