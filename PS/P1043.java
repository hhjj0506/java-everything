package PS;

import java.util.ArrayList;
import java.util.Scanner;

public class P1043 {
    public static int[] parent;
    public static int[] true_person;
    public static ArrayList<Integer>[] party;
    public static int res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int t = sc.nextInt();
        res = 0;
        true_person = new int[t];

        for (int i = 0; i < t; i++) { // save people who know the truth
            true_person[i] = sc.nextInt();
        }
        party = new ArrayList[m];
        for (int i = 0; i < m; i++) { // save party data
            party[i] = new ArrayList<Integer>();
            int party_size = sc.nextInt();
            for (int j = 0; j < party_size; j++) {
                party[i].add(sc.nextInt());
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) { // initialize parent node as self
            parent[i] = i;
        }
        
        for (int i = 0; i < m; i++) { // make people who attended party as one set
            int first = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(first, party[i].get(j));
            }
        }

        for (int i = 0; i < m; i++) { // if parent node of party and parent of person who knows the truth are same, person cannot lie
            boolean isPossible = true;
            int current = party[i].get(0);
            for (int j = 0; j < true_person.length; j++) {
                if (find(current) == find(true_person[j])) {
                    isPossible = false;
                    break;
                }
            }
            
            if (isPossible) {
                res++;
            }
        }

        System.out.println(res);
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
