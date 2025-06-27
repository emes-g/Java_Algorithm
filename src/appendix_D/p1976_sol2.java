package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Union-Find, amortized O(logn)

public class p1976_sol2 {
    static int n, m;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int u = 1; u <= n; u++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int v = 1; v <= n; v++) {
                if (st.nextToken().equals("1")) {
                    union(u, v);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            int v = Integer.parseInt(st.nextToken());
            if (find(u) != find(v)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
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
        return true;
    }

    public static int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }
}
