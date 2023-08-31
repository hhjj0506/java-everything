package PS;

import java.util.Scanner;

public class P1976 {
    public static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] city = new int[n + 1][n + 1];
        int[] route = new int[m + 1];
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) { // save city connection data
            for (int j = 1; j <= n; j++) {
                city[i][j] = sc.nextInt();
            }
        }

        for (int i = 1; i <= m; i++) { // save route data
            route[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) { // initialize parent node as self
            parent[i] = i;
        }

        for (int i = 1; i <=n; i++) { // if city is connected in adjacent array, execute union
            for (int j = 1; j <= n; j++) {
                if (city[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        // check if cityies on route are connected to one parent city (whether there is a possible route)
        int index = find(route[1]);

        for (int i = 2; i < route.length; i++) {
            if (index != find(route[i])) { // if route is not possible, print out no
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
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
}
