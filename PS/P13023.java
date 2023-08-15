package PS;

import java.util.ArrayList;
import java.util.Scanner;

public class P13023 {
    static boolean visited[];
    static ArrayList<Integer>[] A;
    static boolean arrive;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int m;
        arrive = false;

        n = sc.nextInt();
        m = sc.nextInt();
        A = new ArrayList[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            A[start].add(end);
            A[end].add(start);
        }

        for (int i = 0; i < n; i++) {
            DFS(i, 1);
            if (arrive) {
                break;
            }
        }

        if (arrive) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    public static void DFS(int now, int depth) {
        // if depth hits 5, this means that the condition is met so return true
        if (depth == 5 || arrive) {
            arrive = true;
            return;
        }

        visited[now] = true;

        for (int i : A[now]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }

        visited[now] = false;
    }
}
