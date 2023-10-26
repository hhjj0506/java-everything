package PS.Programmers;

import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int a1 = 0, a2 = 0;
        // try multiplying the smallest # in A w/ biggest # in B and vice versa then compare two?
        Arrays.sort(A);
        Arrays.sort(B);
        for (int i = 0; i < A.length; i++) {
            a1 += A[i] * B[A.length-(i+1)];
            a2 += B[i] + A[A.length-(i+1)];
        }
        
        if (a1 < a2) {
        } else {
        }
        return a1;
    }
}
