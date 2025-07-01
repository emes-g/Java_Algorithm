package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// MST, Kruskal
// 누적합 구할때 자료형에 주의

public class p16398_sol1 {
    static int n, m;
    static edge16398[] edges;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = (n * (n - 1)) / 2;
        edges = new edge16398[m];
        p = new int[n];
        Arrays.fill(p, -1);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (i < j) {
                    edges[cnt++] = new edge16398(i, j, weight);
                }
            }
        }
        Arrays.sort(edges);
        cnt = 0;
        long ans = 0;
        for (edge16398 edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            ans += edge.weight;
            if (++cnt == n - 1) {
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

class edge16398 implements Comparable<edge16398> {
    int from, to, weight;

    public edge16398(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge16398 e) {
        return this.weight - e.weight;
    }
}
