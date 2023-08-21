package PS;

import java.util.Scanner;

public class P1541 {
    static int ans = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ex = sc.nextLine();
        String[] str = ex.split("-"); // split string by subtract sign (-). this will leave all the adding operations put together

        for (int i = 0; i < str.length; i++) {
            int temp = calculateSum(str[i]);

            if (i == 0) { // only add the first value
                ans = ans + temp;
            } else { // as for the other values, subtract all added values
                ans = ans - temp;
            }
        }

        System.out.println(ans);
    }

    static int calculateSum(String a) {
        int sum = 0;
        String[] temp = a.split("[+]"); // split all numbers in the adding operation

        for (int i = 0; i < temp.length; i++) {
            sum += Integer.parseInt(temp[i]); // add all numbers in the operation
        }

        return sum;
    }
}
