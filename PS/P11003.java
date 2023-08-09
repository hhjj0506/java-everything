package PS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); // this is used for printing all the result at once
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(bf.readLine());
        Deque<Node> deque = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int now = Integer.parseInt(st.nextToken()); // take in new data as window slide
            // if the new value is smaller than the last value in the deque, remove the last value until it's smaller than new val
            while (!deque.isEmpty() && deque.getLast().val > now) {
                deque.removeLast();
            }
            deque.addLast(new Node(now, i)); // add new val to deque

            // if the first value in the deque is out of the window range, remove it
            if (deque.getFirst().index <= i - L) {
                deque.removeFirst();
            }
            bw.write(deque.getFirst().val + " "); // write the smallest val in the deque after sliding to BufferedWriter
        }
        bw.flush();
        bw.close();
    }

    static class Node {
        public int val;
        public int index;

        Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
