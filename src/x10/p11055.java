package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11055 {
    static int n;
    // dp[i] : i번째 수를 포함할 때, 부분수열 합의 최댓값
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
            dp[i] = num[i];
            for (int j = i - 1; j > 0; j--) {   // 조건을 만족하는 최초의 j에 대해서만 수행하면 안 됨 (그리디 방식 X)
                if (num[j] < num[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + num[i]);
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
