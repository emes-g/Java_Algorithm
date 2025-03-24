package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p9084 {
    static int n, m;
    // dp[i] : n가지 동전으로 i원을 만드는 방법의 수
    static int[] coins, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            coins = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            m = Integer.parseInt(br.readLine());
            dp = new int[m + 1];
            dp[0] = 1;  // 예외적
            for (int i = 1; i <= n; i++) {  // 반복문 순서에 주의 (동전 → 돈)
                for (int j = coins[i]; j <= m; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            System.out.println(dp[m]);
        }
    }
}
