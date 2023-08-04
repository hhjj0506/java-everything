package PS;

import java.util.Scanner;

public class P11659 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        // set the range to N+1 to use [0] as 0, and other indexes as it is
        int arr[] = new int[N+1];
        int add_arr[] = new int[N+1];
        int res[] = new int[M];

        add_arr[0] = 0;

        for (int i = 1; i < N+1; i++) {
            // 배열안 숫자들을 입력받으며 합배열도 같이 생성
            arr[i] = sc.nextInt();
            add_arr[i] = add_arr[i-1] + arr[i];
        }

        for (int i = 0; i < M; i++) {
            int num1 = sc.nextInt();
            int num2 = sc.nextInt();
            
            res[i] = add_arr[num2] - add_arr[num1-1];
        }

        for (int i = 0; i < M; i++) System.out.println(res[i]);
    }
}
