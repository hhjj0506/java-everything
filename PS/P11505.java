package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11505 {
    static long[] tree;
    static int MOD;
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
        MOD = 1000000007;
        tree = new long[treeSize + 1];

        for (int i = 0; i < tree.length; i++) { // set initial value as 1 for multiplying
            tree[i] = 1;
        }

        for (int i = leftStartIndex + 1; i <= leftStartIndex + n; i++) {
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
                System.out.println(getMulti(s, (int)e));
            } else {
                return;
            }
        }
    }

    private static long getMulti(int s, int e) {
        long partMulti = 1;
        while (s <= e) {
            if (s % 2 == 1) {
                partMulti = partMulti * tree[s] % MOD;
                s++;
            }
            if (e % 2 == 0) {
                partMulti = partMulti * tree[e] % MOD;
                e--;
            }
            s = s / 2;
            e = e / 2;
        }
        return partMulti;
    }

    private static void changeVal(int index, long val) {
        tree[index] = val;
        while (index > 1) { // get current node's child nodes and multiply them
            index = index / 2;
            tree[index] = tree[index * 2] % MOD * tree[index * 2 + 1] % MOD;
        }
    }

    private static void setTree(int i) {
        while (i != 1) {
            tree[i / 2] = tree[i / 2] * tree[i] % MOD;
            i--;
        }
    }
}
