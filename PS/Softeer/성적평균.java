package PS.Softeer;

import java.io.*;
import java.util.*;

public class 성적평균 {

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      int i = 0;
      st = new StringTokenizer(br.readLine());
      double[] arr = new double[n];
      double[] ans = new double[k];

      while (st.hasMoreTokens()) {
        arr[i] = Double.parseDouble(st.nextToken());
        i++;
      }

      for (i = 0; i < k; i++) {
        double num = 0;
        double cnt = 0.0;
        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        for (j = j; j <= l; j++) {
          num += arr[j-1];
          cnt++;
        }

        ans[i] = num / cnt;
      }

      for (i = 0; i < k; i++) {
        System.out.println(String.format("%.2f", ans[i]));
      }
    }
}

