package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9251 {
    static char[] a, b;
    // dp[i][j] : 문자열 a의 i번째 문자, 문자열 b의 j번째 문자까지 스캔할 때, LCS의 길이
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        dp = new int[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if (a[i - 1] == b[j - 1]) { // 같은 문자가 추가된 경우에만 값이 증가
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[a.length][b.length]);
    }
}
