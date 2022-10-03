package swordfinger_offer.DP.斐波那契数列;

public class Solution {
    public int Fibonacci(int n) {
        if(n == 1 || n == 2){
            return 1;
        }
        int [] values = new int [n+1];
        values[1] = 1;
        values[2] = 1;
        for(int i = 3 ; i < values.length ; i ++){
            values[i] = values[i-1] + values[i-2];
        }
        return values[n];
    }
}
