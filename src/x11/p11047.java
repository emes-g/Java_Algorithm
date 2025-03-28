package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11047 {
    static int n, k;
    // dp로 해결하려면 시간복잡도가 O(NK)인데, NK가 10억까지 가능해서 시간초과
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n + 1];
        for (int i = n; i > 0; i--) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += (k / coins[i]);
            k %= coins[i];
        }
        System.out.println(ans);
    }
}
