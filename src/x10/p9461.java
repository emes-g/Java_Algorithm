package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9461 {
    static long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp[1] = dp[2] = dp[3] = 1;
        for (int i = 4; i < 101; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }
        System.out.println(sb);
    }
}
