package PS;

import java.util.Scanner;

public class P1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        boolean[] check = new boolean[(int)(max - min + 1)]; // array in the range of difference in min and max
        int cnt = 0;

        // start from 4, which is smallest power of 2, and repeat until max num
        for (long i = 2; i * i <= max; i++) {
            long powerNum = i * i;
            long start = min / powerNum;

            // if min is not divided clean by powernum, the start should be added to check the power numbers
            if (min % powerNum != 0) {
                start++;
            }

            // if the number is power of any other #(meaning not prime #), check the number as true
            for (long j = start; powerNum * j <= max; j++) {
                check[(int)((j * powerNum) - min)] = true;
            }
        }

        // increase the count if the num is prime
        for (int i = 0; i <= max - min; i++) {
            if (!check[i]) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
