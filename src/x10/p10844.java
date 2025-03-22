package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10844 {
    static final int MOD = 1000000000;
    static int n;
    // dp[i][j] : 길이가 i이고, j로 시작하는 계단 수의 개수
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1][10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }
        dp[0][1] = 1;   // 예외적 : dp[2][1]을 정의하기 위함
        for (int i = 2; i <= n; i++) {
            dp[i][1] = (dp[i - 2][1] + dp[i - 1][2]) % MOD;
            dp[i][9] = (dp[i - 1][8]);
            for (int j = 2; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
            }
        }
        long ans = 0;
        for (int i = 1; i <= 9; i++) {
            ans += dp[n][i];
        }
        ans %= MOD;
        System.out.println(ans);
    }
}
