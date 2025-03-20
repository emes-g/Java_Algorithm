package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1912 {
    static int n;
    // dp[i] : i번째 수를 포함할 때, 연속합의 최댓값
    static int[] num, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        dp = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = num[1];
        for (int i = 2; i <= n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = num[i];
                continue;
            }
            dp[i] = dp[i - 1] + num[i];
        }
        int ans = -1000;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
