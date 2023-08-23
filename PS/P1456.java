package PS;

import java.util.Scanner;

public class P1456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long min = sc.nextLong();
        long max = sc.nextLong();
        long[] A = new long[10000001];
        int cnt = 0;

        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }

        for (int i = 2; i <= Math.sqrt(A.length); i++) { // repeat only until sqrt of n
            if (A[i] == 0) { // continue if num is not prime number
                continue;
            }
            for (int j = i + i; j < A.length; j = j + i) { // delete numbers that are multiple of prime numbers
                A[j] = 0;
            }
        }

        // for every prime numbers, calculate the squared #s of the prime numbers that are in range
        for (int i = 2; i < 10000001; i++) {
            if (A[i] != 0) { 
                long temp = A[i];

                // double is used because the # can exceed long's range
                while ((double)A[i] <= (double)max / (double)temp) { // while current prime number is smaller than max
                    if ((double)A[i] >= (double)min / (double)temp) { // if current prime number is bigger than min
                        cnt++; // add to count
                    }
                    temp = temp * A[i]; 
                }
            }
        }

        System.out.println(cnt);
    }
}
