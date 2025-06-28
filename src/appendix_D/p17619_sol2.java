package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [Union-Find + 투포인터]
// 애초에 메인코드가 짧기 때문에 어려운 코드는 아닌데,
// 아무리봐도 그리디가 직관적으로 풀기 좋음
// (바로 생각나기도 했고)

public class p17619_sol2 {
    static int n, q;    // n <= 십만, q <= 십만
    static tuple17619[] arr;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new tuple17619[n + 1];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        arr[0] = new tuple17619(-1, -1, -1);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            arr[i] = new tuple17619(x1, x2, i);
        }
        Arrays.sort(arr);
        for (int s = 1, e = s + 1; s <= n; s++) {
            while (e <= n && (arr[s].x2 >= arr[e].x1)) {    // s == e인 경우, 항상 true를 반환
                union(arr[s].orgNum, arr[e++].orgNum);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(find(u) == find(v) ? 1 : 0).append('\n');
        }
        System.out.println(sb);
    }

    public static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (p[u] >= p[v]) {
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