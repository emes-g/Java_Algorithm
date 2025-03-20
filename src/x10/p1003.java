package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1003 {
    static int[] dp = new int[41];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < 41; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                sb.append("1 0\n");
                continue;
            }
            sb.append(dp[n - 1]).append(' ').append(dp[n]).append('\n');
        }
        System.out.println(sb);
    }
}
