package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13398 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        int[] l = new int[n];
        int[] r = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        l[0] = a[0];
        int res = l[0];

        for (int i = 1; i < n; i++) {
            l[i] = Math.max(a[i], l[i - 1] + a[i]);
            res = Math.max(res, l[i]);
        }

        r[n - 1] = a[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            r[i] = Math.max(a[i], r[i + 1] + a[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            int temp = l[i - 1] + r[i + 1];
            res = Math.max(res, temp);
        }

        System.out.println(res);
    }
}
