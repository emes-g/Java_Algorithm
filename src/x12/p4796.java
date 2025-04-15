package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p4796 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (l == 0) {
                System.out.println(sb);
                return;
            }
            int val = v / p;    // quotient
            int rest = Math.min(v % p, (int) l);   // remainder
            sb.append("Case ").append(t++).append(": ").append(val * l + rest).append('\n');
        }
    }
}
