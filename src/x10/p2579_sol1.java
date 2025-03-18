package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2579_sol1 {
    static int n;
    static int[] score;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        score = new int[n + 1];
        dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {
            System.out.println(score[1]);
            return;
        }
        dp[1][1] = score[1];
        dp[1][2] = 0;
        dp[2][1] = score[2];
        dp[2][2] = score[1] + score[2];
        for (int i = 3; i <= n; i++) {
            dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][2]) + score[i];
            dp[i][2] = dp[i - 1][1] + score[i];
        }
        System.out.println(Math.max(dp[n][1], dp[n][2]));
    }
}
