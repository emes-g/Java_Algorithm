package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Union-Find
// 시간복잡도 : O(T) * (amortized O(klogn) + O(m))

public class p7511_sol2 {
    static int n, k, m; // n <= 백만, k <= 십만, m <= 십만
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 1;
        StringBuilder sb = new StringBuilder();
        while (t <= T) {
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            p = new int[n + 1];
            Arrays.fill(p, -1);
            for (int i = 0; i < k; i++) {   // O(K)
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                union(u, v);
            }
            sb.append("Scenario ").append(t++).append(":\n");
            m = Integer.parseInt(br.readLine());
            while (m-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                sb.append(find(u) == find(v) ? 1 : 0).append('\n');
            }
            sb.append('\n');
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
        return true;
    }

    public static int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }
}
