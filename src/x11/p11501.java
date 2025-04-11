package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p11501 {
    static int n;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            prices = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                prices[i] = Integer.parseInt(st.nextToken());
            }
            int max = prices[n];
            long sum = 0;
            // 배열을 뒤에서부터 순회하며, (앞에서부터 순회하면 샀어야 하는 주식인지를 O(n)에 판단할 수 없음)
            // 고점보다 싼 주식이면 무조건 구매
            for (int i = n - 1; i > 0; i--) {
                if (max < prices[i]) {
                    max = prices[i];
                } else {
                    sum += (max - prices[i]);
                }
            }
            sb.append(sum).append('\n');
        }
        System.out.println(sb);
    }
}
