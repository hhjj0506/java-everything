package PS;

import java.util.PriorityQueue;
import java.util.Scanner;

public class P1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // use priority queue for automatic sorting. (numbers should be sorted ascend for the smallest sum)
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 

        for (int i = 0; i < n; i++) {
            int data = sc.nextInt();
            pq.add(data);
        }

        int data1 = 0;
        int data2 = 0;
        int sum = 0;

        while (pq.size() != 1) {
            data1 = pq.remove(); // take out first group of cards
            data2 = pq.remove(); // take out second group of cards
            sum += data1 + data2; // add sum of two group of cards to sum
            pq.add(data1 + data2); // add sum of two group of cards back to pq for continuous adding
        }

        System.out.println(sum);
    }
}
