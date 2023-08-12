package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1377 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        mData[] A = new mData[N];

        // save the data of original value paired with its index
        for (int i = 0; i < N; i++) {
            A[i] = new mData(Integer.parseInt(bf.readLine()), i);
        }

        Arrays.sort(A); // sort the array first (O(nlogn))
        int max = 0;

        /*
        compare the index of the unsorted array and sorted array.
        the index that has the biggest (positive) difference between previous and current indexes is the time
        when the array is sorted. 
        */ 
        for (int i = 0; i < N; i++) {
            if (max < A[i].index - i) {
                max = A[i].index - i;
            }
        }

        System.out.println(max + 1);
    }
}

class mData implements Comparable<mData> {
    int val;
    int index;

    public mData(int val, int index) {
        super();
        this.val = val;
        this.index = index;
    }

    @Override
    public int compareTo(mData o) {
        return this.val - o.val;
    }
}
