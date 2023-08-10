package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class P17298 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] A = new int[n];
        int[] ans = new int[n];
        String[] str = bf.readLine().split(" ");
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(str[i]);
        }

        stack.push(0); // push in the first index value into stack

        for (int i = 1; i < n; i++) {
            // when stack is not empty and the top of stack is smaller than value at current index
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                ans[stack.pop()] = A[i]; // save the most left num in the right side of the numbers where # is bigger than current peek
            }
            stack.push(i);
        }

        // if no right number is fund for the index, make the answer -1
        while (!stack.empty()) {
            ans[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < n; i++) {
            bw.write(ans[i] + " ");
        }

        bw.write("\n");
        bw.flush();
    }
}
