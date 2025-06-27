package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// only PathCompression, amortized O(logn)

public class p1717_sol2 {
    static int n, m;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n + 1];
        Arrays.fill(p, -1);
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) { // O(m) * amortized O(logn)
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (cmd == 0) {
                union(u, v);
            } else {
                sb.append(find(u) == find(v) ? "YES\n" : "NO\n");
            }
        }
        System.out.println(sb);
    }

    public static int find(int x) {
        if (p[x] < 0) { // 루트노드를 발견한 경우
            return x;
        }
        return p[x] = find(p[x]);
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {   // 같은 트리(그룹)에 속한 경우
            return false;
        }
        p[v] = u;
        return true;
    }
}
