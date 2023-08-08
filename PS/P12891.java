package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12891 {
    static int checkArr[];
    static int arr[];
    static int checkReq;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int res = 0;
        char[] A = new char[S]; // store all string
        checkArr = new int[4]; // store check array
        arr = new int[4]; // store current array
        checkReq = 0;
        A = bf.readLine().toCharArray();
        st = new StringTokenizer(bf.readLine());

        for (int i = 0; i < 4; i++) { // make check condition array
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkReq++;
            }
        }

        for (int i = 0; i < P; i++) { // add chars to the first window
            Add(A[i]);
        }

        if (checkReq == 4) { // if the first window satisfies the condition, add to the result
            res++;
        }

        // repeat the above process until the window reaches the end of array
        for (int i = P; i < S; i++) { // sliding window
            int j = i - P;
            Add(A[i]);
            Remove(A[j]);
            if (checkReq == 4) {
                res++;
            }
        }

        System.out.println(res);
    }

    private static void Add(char c) {
        switch (c) {
            case 'A':
                arr[0]++;
                if (arr[0] == checkArr[0]) {
                    checkReq++;
                }
                break;
            case 'C':
                arr[1]++;
                if (arr[1] == checkArr[1]) {
                    checkReq++;
                }
                break;
            case 'G':
                arr[2]++;
                if (arr[2] == checkArr[2]) {
                    checkReq++;
                }
                break;
            case 'T':
                arr[3]++;
                if (arr[3] == checkArr[3]) {
                    checkReq++;
                }
                break;    
        }
    }
    
    private static void Remove(char c) {
        switch (c) {
            case 'A':
                if (arr[0] == checkArr[0]) {
                    checkReq--;
                }
                arr[0]--;
                break;
            case 'C':
                if (arr[1] == checkArr[1]) {
                    checkReq--;
                }
                arr[1]--;
                break;
            case 'G':
                if (arr[2] == checkArr[2]) {
                    checkReq--;
                }
                arr[2]--;
                break;
            case 'T':
                if (arr[3] == checkArr[3]) {
                    checkReq--;
                }
                arr[3]--;
                break;    
        }
    }
}