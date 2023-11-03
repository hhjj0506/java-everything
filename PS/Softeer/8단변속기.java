package PS.Softeer;

import java.io.*;
import java.util.*;

public class 8단변속기 {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int checkAsc = 1;
      int checkDesc = 8;
      boolean isAsce = true;
      boolean isDesc = true;
      boolean isMixed = true;

      while (st.hasMoreTokens()) {
        int num = Integer.parseInt(st.nextToken());
        if (num == checkAsc && isAsce) {
          checkAsc++;
          isDesc = false;
        } else if (num == checkDesc && isDesc) {
          checkDesc--;
          isAsce = false;
        } else {
          isAsce = false;
          isDesc = false;
          break;
        }
      }

      if (isAsce) {
        System.out.println("ascending");
      } else if (isDesc) {
        System.out.println("descending");
      } else if (isMixed) {
        System.out.println("mixed");
      }
    }
}

