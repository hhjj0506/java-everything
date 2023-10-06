package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14003 {
    static int n, maxLen;
    static int[] b = new int[1000001];
    static int[] a = new int[1000001];
    static int[] d = new int[1000001];
    static int[] ans = new int[1000001];
    public static void main(String[] args) throws NumberFormatException, IOException {
        int index;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        b[++maxLen] = a[1];
        d[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (b[maxLen] < a[i]) {
                b[++maxLen] = a[i];
                d[i] = maxLen;
            } else {
                index = binarySearch(1, maxLen, a[i]);
                b[index] = a[i];
                d[i] = index;
            }
        }

        System.out.println(maxLen);
        index = maxLen;
        int x = b[maxLen] + 1;

        for (int i = n; i >= 1; i--) {
            if (d[i] == index && a[i] < x) {
                ans[index] = a[i];
                x = a[i];
                index--;
            }
        }

        for (int i = 1; i <= maxLen; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    static int binarySearch(int l, int r, int now) {
        int mid;

        while (l < r) {
            mid = (l + r) / 2;
            if (b[mid] < now) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
