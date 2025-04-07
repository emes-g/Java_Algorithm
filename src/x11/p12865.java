package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p12865 {
    static int n, k;
    static pair12865[] items;
    // dp[i][j] : i번째 물건까지 고려하고, 무게 j까지 담을 수 있는 가방이 있을 때, 해당 가방이 보유할 수 있는 최대 가치
    // dp[i][j] = max(i번째 물건을 담지 않는 경우, i번째 물건을 담는 경우)
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        items = new pair12865[n + 1];
        items[0] = new pair12865(0, 0);
        dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new pair12865(weight, value);
        }
        Arrays.sort(items);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= items[i].weight) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - items[i].weight] + items[i].value);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}

class pair12865 implements Comparable<pair12865> {
    int weight, value;

    public pair12865(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(pair12865 o) {
        if (this.weight != o.weight) {
            return this.weight - o.weight;
        }
        return o.value - this.value;
    }
}