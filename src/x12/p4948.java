package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p4948 {
    static final int MAX = 250000;
    static boolean[] isPrime = new boolean[MAX + 1];
    static int[] dp = new int[MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= MAX; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j <= MAX; j += i) {
                isPrime[j] = false;
            }
        }
        for (int i = 2; i <= MAX; i++) {
            dp[i] = dp[i - 1];
            if (isPrime[i]) {
                dp[i]++;
            }
        }
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                System.out.println(sb);
                return;
            }
            sb.append(dp[n * 2] - dp[n]).append('\n');
        }
    }
}
