package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1038_sol1 {
    static int[][] dp = new int[11][10];
    static int[][] sum = new int[11][10];
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp[1][0] = 1;
        for (int i = 1; i < 11; i++) {
            sum[i][0] = sum[i - 1][9];
            for (int j = 1; j < 10; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                sum[i][j] = sum[i][j - 1] + dp[i][j];
            }
        }
        String ans = func();
        if (ans == null) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);
    }

    public static String func() {   // find first letter
        if (n / 10 == 0) {
            return String.valueOf(n);
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 10; j++) {
                if (n > sum[i][j - 1] && n <= sum[i][j]) {
                    n -= (sum[i][j] - sum[i - 1][j - 1]);
                    return j + func();
                }
            }
        }
        return null;
    }
}
