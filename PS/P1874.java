package PS;

import java.util.Scanner;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
             A[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();
        int num = 1;
        boolean res = true;

        for (int i = 0; i < A.length; i++) {
            int curNum = A[i]; // current number in the array
            if (curNum >= num) {
                while (curNum >= num) { // push the numbers into stack until it meets the current number in the array
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else { // if current number is smaller than the target, pop out the num
                int n = stack.pop();
                if (n > curNum) {
                    System.out.println("NO");
                    res = false;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        if (res) {
            // if the the given condition is met, print out the result
            System.out.println(bf.toString());
        }
    }
}
