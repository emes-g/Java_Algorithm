package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// MST, Kruskal 응용

public class p1647 {
    static int n, m, ans;
    static edge1647[] edges;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new edge1647[m];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new edge1647(from, to, weight);
        }
        if (n == 2) {   // 경계값 분석
            System.out.println(0);
            return;
        }
        Arrays.sort(edges);
        int cnt = 0;
        for (edge1647 edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            ans += edge.weight;
            // 두 개의 분리된 마을로 만들기 위해,
            // (n-1)번째 간선은 연결하지 않음
            if (++cnt == n - 2) {
                break;
            }
        }
        System.out.println(ans);
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

class edge1647 implements Comparable<edge1647> {
    int from, to, weight;

    public edge1647(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge1647 e) {
        return this.weight - e.weight;
    }
}