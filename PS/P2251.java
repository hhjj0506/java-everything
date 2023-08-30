package PS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P2251 {
    // two arrays to show all 6 ways water can be moved
    static int[] send = {0, 0, 1, 1, 2, 2};
    static int[] receive = {1, 2, 0, 2, 0, 1};
    static boolean[][] visited; // C's weight is fixed, so only check A & B
    static boolean[] ans;
    static int[] now;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        now = new int[3]; // stores the weight of waters in A, B, C
        now[0] = sc.nextInt();
        now[1] = sc.nextInt();
        now[2] = sc.nextInt();
        visited = new boolean[201][201];
        ans = new boolean[201];
        BFS();

        for (int i = 0; i < ans.length; i++) {
            if (ans[i]) {
                System.out.print(i + " ");
            }
        }
    }

    public static void BFS() {
        Queue<AB> queue = new LinkedList<>();
        queue.add(new AB(0, 0));
        visited[0][0] = true;
        ans[now[2]] = true;
        
        while (!queue.isEmpty()) {
            AB p = queue.poll();
            int A = p.A;
            int B = p.B;
            int C = now[2] - A - B; // C is total water - A & B

            for (int i = 0; i < 6; i++) { // A->B, A->C, B->A, B->C, C->A, C->B
                int[] next = {A, B, C};
                next[receive[i]] += next[send[i]];
                next[send[i]] = 0;
                if (next[receive[i]] > now[receive[i]]) { // when water overflows
                    // put overflowing waters into previous watertank
                    next[send[i]] = next[receive[i]] - now[receive[i]];
                    next[receive[i]] = now[receive[i]]; // set overflowing tank as full
                }
                if (!visited[next[0]][next[1]]) { // check if visited using water weight in A & B
                    visited[next[0]][next[1]] = true;
                    queue.add(new AB(next[0], next[1]));
                    if (next[0] == 0) { // when water weight at A is 0, add C's water weight to answer
                        ans[next[2]] = true;
                    }
                }
            }
        }
    }
}

class AB { // we only need value of A & B
    int A;
    int B;

    public AB(int A, int B) {
        this.A = A;
        this.B = B;
    }
}
