package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = y - x;
            int n = (int) Math.sqrt(dist);  // always Math.sqrt(n * n) == n
            if (dist == n * n) {
                sb.append(n * 2 - 1).append('\n');
            } else if (dist <= n * (n + 1)) {
                sb.append(n * 2).append('\n');
            } else {
                sb.append(n * 2 + 1).append('\n');
            }
        }
        System.out.println(sb);
    }
}