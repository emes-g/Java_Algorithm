package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p15486 {
    static int n;
    // dp[i] : i일부터 상담을 시작했을 때, 백준이가 얻을 수 있는 최대 이익
    static int[] time, price, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        time = new int[n + 1];
        price = new int[n + 1];
        dp = new int[n + 2];
        dp[n + 1] = 0;  // 초기값 명시적으로 초기화
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            price[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = n; i > 0; i--) {
            dp[i] = dp[i + 1];  // 디폴트 : i일에 잡혀있는 상담을 수행하지 않는 경우
            int nextCounsel = i + time[i];
            if (nextCounsel > n + 1) {  // i일에 잡혀있는 상담을 수행할 수 없는 경우
                continue;
            }
            dp[i] = Math.max(dp[i], price[i] + dp[nextCounsel]);
        }
        System.out.println(dp[1]);
    }
}