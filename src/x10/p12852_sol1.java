package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p12852_sol1 {
    static int n;
    // dp[i] : i를 1로 만들기 위해 필요한 연산의 최솟값
    static int[] dp, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        arr = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            arr[i] = i - 1;
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                arr[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                arr[i] = i / 3;
            }
        }
        StringBuilder sb = new StringBuilder();
        int val = n;
        while (true) {
            sb.append(val).append(' ');
            if (val == 1) {
                break;
            }
            val = arr[val];
        }
        System.out.println(dp[n]);
        System.out.println(sb);
    }
}
