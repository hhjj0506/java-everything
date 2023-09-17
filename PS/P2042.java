package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2042 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int treeHeight = 0;
        int length = n;

        while (length != 0) {
            length /= 2;
            treeHeight++;
        }
        int treeSize = (int)Math.pow(2, treeHeight + 1);
        int leftStartIndex = treeSize / 2 - 1;
        tree = new long[treeSize + 1];

        for (int i = leftStartIndex + 1; i <= leftStartIndex + n; i++) { // put data into leaf nodes
            tree[i] = Long.parseLong(br.readLine());
        }

        setTree(treeSize - 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long e = Long.parseLong(st.nextToken());

            if (a == 1) {
                changeVal(leftStartIndex + s, e);
            } else if (a == 2) {
                s = s + leftStartIndex;
                e = e + leftStartIndex;
                System.out.println(getSum(s, (int)e));
            } else {
                return;
            }
        }
    }

    private static long getSum(int s, int e) { // get the sum of tree
        long partSum = 0;
        while (s <= e) {
            if (s % 2 == 1) {
                partSum = partSum + tree[s];
                s++;
            }
            if (e % 2 == 0) {
                partSum = partSum + tree[e];
                e--;
            }
            s = s / 2;
            e = e / 2;
        }

        return partSum;
    }

    private static void changeVal(int index, long val) { // change the value of nodes in the tree
        long diff = val - tree[index];
        while (index > 0) {
            tree[index] = tree[index] + diff;
            index = index / 2;
        }
    }

    private static void setTree(int i) { // initialize tree
        while (i != 1) {
            tree[i / 2] = tree[i / 2] + tree[i];
            i--;
        }
    }
}
