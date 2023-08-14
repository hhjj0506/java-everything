package PS;

import java.util.Scanner;

public class P2023 {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // check only thest four first place numbers because all the other numbers are not prime numbers
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    static void DFS (int num, int place) {
        if (place == n) {
            if (isPrime(num)) {
                System.out.println(num);
            }
            return;
        }

        for (int i = 1; i < 10; i++) {
            // if the number is even, don't have to check
            if (i % 2 == 0) {
                continue;
            }

            // if the number is prime, move the number place and check that number
            if (isPrime(num * 10 + i)) {
                DFS(num * 10 + i, place + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
