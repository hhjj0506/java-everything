package PS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        int original[][] = new int[N+1][N+1]; // original array
        int arr[][] = new int[N+1][N+1]; // added array

        for (int i = 1; i < N+1; i++) { // getting each lines for the 2 dimesional array
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j < N+1; j++) {
                original[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 1; i < N+1; i++) { // make added array using original int data
            for (int j = 1; j < N+1; j++) {
                arr[i][j] = arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1] + original[i][j];
            }
        }

        for (int i = 0; i < M; i++) { // 
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int x1 = Integer.parseInt(stringTokenizer.nextToken());
            int y1 = Integer.parseInt(stringTokenizer.nextToken());
            int x2 = Integer.parseInt(stringTokenizer.nextToken());
            int y2 = Integer.parseInt(stringTokenizer.nextToken());

            int res = arr[x2][y2] - arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1];
            System.out.println(res);
        }
    }
}
