package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Union-Find
// 시간복잡도 O(n) * amortized O(logn)
// 부품들의 번호가 듬성듬성 존재할 수 있음

public class p18116 {
    static final int MAX = 1000000;
    static int n;   // n <= 백만(MAX)
    static int[] p, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[MAX + 1];
        size = new int[MAX + 1];
        Arrays.fill(p, -1);
        Arrays.fill(size, 1);
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("I")) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            } else {
                int c = Integer.parseInt(st.nextToken());
                sb.append(size[find(c)]).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (p[u] > p[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        if (p[u] == p[v]) {
            p[u]--;
        }
        p[v] = u;
        size[u] += size[v];
        size[v] = -1;
        return true;
    }

    public static int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }
}
