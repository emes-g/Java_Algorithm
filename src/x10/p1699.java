package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1699 {
    static int n;
    // dp[i] : i를 제곱수의 합으로 나타낼 때, 그 제곱수 항의 최소 개수
    // 시간복잡도 : O(nlogn)
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        for (int i = 1; i * i <= n; i++) {
            dp[i * i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == 1) {
                continue;
            }
            dp[i] = i;
            for (int j = 1; j * j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(dp[n]);
    }
}
