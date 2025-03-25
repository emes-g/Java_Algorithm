package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p10942 {
    static int n;
    static int[] num;
    // dp[i][j] : i번째 수부터 j번째 수까지 팰린드롬을 이루는지 여부
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        dp = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][i] = true;
        }
        // dp[1][2] : num[1] == num[2] ? true : false
        for (int i = 1; i < n; i++) {
            if (num[i] == num[i + 1]) {
                dp[i][i + 1] = true;
            } else {
                dp[i][i + 1] = false;
            }
        }
        // dp[1][4] : dp[2][3]이 팰린드롬이고, num[1] == num[4]이면 dp[1][4]도 팰린드롬
        for (int diff = 2; diff < n; diff++) {
            for (int i = 1; i + diff <= n; i++) {
                if (dp[i + 1][i + diff - 1] && num[i] == num[i + diff]) {
                    dp[i][i + diff] = true;
                } else {
                    dp[i][i + diff] = false;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (dp[s][e]) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.println(sb);
    }
}
