package PS;

import java.util.Scanner;

public class P1717 {
    public static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) { // initialize parent node as self
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (num == 0) { // combine a & b into one set
                union(a, b);
            } else {
                if (checkSame(a, b)) { // check if a & b are in same set
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    public static void union(int a, int b) { // union operation (connect parent nodes to each other)
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) { // find operation
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]); // reduce path by using recursion
        }
    }

    public static boolean checkSame(int a, int b) { // check if two values are in same set
        a = find(a);
        b = find(b);

        if (a == b) {
            return true;
        }
        return false;
    }
}
