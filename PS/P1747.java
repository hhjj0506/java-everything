package PS;

import java.util.Scanner;

public class P1747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[10000001];
        int p = n;

        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }

        for (int i = 2; i < Math.sqrt(A.length); i++) {
            if (A[i] == 0) {
                continue;
            }
            for (int j = i + i; j < A.length; j = j + i) {
                A[j] = 0;
            }
        }

        while (true) {
            if (A[p] != 0) { // check for every prime numbers if they are palindrome and find the lowest one
                int res = A[p];
                if (isPalindrome(res)) {
                    System.out.println(res);
                    break;
                }
            }
            p++;
        }
    }

    private static boolean isPalindrome(int num) {
        char temp[] = String.valueOf(num).toCharArray(); // convert int into char array
        int start = 0;
        int end = temp.length - 1;

        while (start < end) {
            // return false if two characters do not match
            if (temp[start] != temp[end]) { 
                return false;
            }
            start++;
            end--;
        }

        return true; // return true if all characters match
    }
}
