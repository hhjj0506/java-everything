package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13251 {
    public static void main(String[] args) throws IOException {
        int m, k, t;
        int d[] = new int[51];
        double prob[] = new double[51];
        double ans = 0.0;
        t = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            d[i] = Integer.parseInt(st.nextToken());
            t += d[i];
        }

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            if (d[i] >= k) {
                prob[i] = 1.0;
                for (int j = 0; j < k; j++) {
                    prob[i] *= (double)(d[i] - j) / (t - j);
                }
            }
            ans += prob[i];
        }

        System.out.println(ans);
    }
}
