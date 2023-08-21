package PS;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        PriorityQueue<Integer> posPq = new PriorityQueue<>(Collections.reverseOrder()); // saves all positive numbers except 1 (sorted in descending order for easier calculation)
        PriorityQueue<Integer> negPq = new PriorityQueue<>(); // saves all negative numbers
        int one = 0; // saves the number of ones
        int zero = 0; // saves the number of zeroes
        int sum = 0;

        for (int i = 0; i < n; i++) { // save data into 4 separate groups
            int data = sc.nextInt();
            if (data > 1) {
                posPq.add(data);
            } else if (data == 1) {
                one++;
            } else if (data == 0) {
                zero++;
            } else {
                negPq.add(data);
            }
        }

        while (posPq.size() > 1) { // take out two biggest numbers in the positive queue, then multiply it and add to sum. repeat until 1 or no number left
            int data1 = posPq.remove();
            int data2 = posPq.remove();
            sum = sum + data1 * data2;
        }

        if (!posPq.isEmpty()) { // if one number is left in the positive queue, add it to the sum
            sum = sum + posPq.remove();
        }

        while (negPq.size() > 1) { // take out two smallest numbers in the negative queue, then multiply it and add to sum. repeat until 1 or no number left
            int data1 = negPq.remove();
            int data2 = negPq.remove();
            sum = sum + data1 * data2;
        }

        if (!negPq.isEmpty()) { // if one number is left in the negative queue AND there is no zero, add it to the sum
            // whether zero exists or not is checked because if there are one or more zeroes, the one negative number left will be multiplied to 0, and produce 0.
            if (zero == 0) {
                sum = sum + negPq.remove();
            }
        }

        sum = sum + one; // add the number of ones to the sum
        System.out.println(sum);
    }
}
