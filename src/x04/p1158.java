package x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] prev = new int[5001];
        int[] next = new int[5001];
        int[] order = new int[n];
        int len = 0;
        for (int i = 1; i <= n; i++) {
            prev[i] = (i == 1) ? n : i - 1;
            next[i] = (i == n) ? 1 : i + 1;
            len++;
        }

        int index = 0;
        for (int i = 1, cnt = 0; len != 0; i = next[i]) {
            if (++cnt == k) {
                prev[next[i]] = prev[i];
                next[prev[i]] = next[i];
                order[index++] = i;
                len--;
                cnt = 0;
            }
        }

        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < n - 1; i++) {
            sb.append(order[i]).append(", ");
        }
        sb.append(order[n - 1]).append(">");
        System.out.println(sb);
    }
}
