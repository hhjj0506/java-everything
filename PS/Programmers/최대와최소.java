package PS.Programmers;

import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(s);
        
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }
        
        answer = min + " " + max;
        
        return answer;
    }
}
