package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p12865 {

    static int n, k;
    static Pair12865[] items;
    static int[][] dp;  // dp[i][j]: i번째 물건까지 고려하고, 가방 최대 용량이 j일 때 가치의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        items = new Pair12865[n + 1];
//        items[0] = new Pair12865(0, 0);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Pair12865(weight, value);
        }
        dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= items[i].weight) {
                    // dp[i][j] = max(i번째 물건을 담지 않는 경우, i번째 물건을 담는 경우)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - items[i].weight] + items[i].value);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}

class Pair12865 {
    int weight, value;

    public Pair12865(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}