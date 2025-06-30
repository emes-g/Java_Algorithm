package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 그래프 모델링 + MST

public class p1368 {
    static int n, m, ans;
    static edge1368[] edges;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = n * (n + 1) / 2;    // 완전 그래프의 간선 개수
        edges = new edge1368[m];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        int cnt = 0;    // 추가한 간선 개수
        for (; cnt < n; cnt++) {
            int weight = Integer.parseInt(br.readLine());
            edges[cnt] = new edge1368(cnt, n, weight);
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(st.nextToken());
                if (i < j) {
                    edges[cnt++] = new edge1368(i, j, weight);
                }
            }
        }
        Arrays.sort(edges);
        cnt = 0;    // MST에 포함한 간선 개수
        for (edge1368 edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            ans += edge.weight;
            if (cnt == n) {
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

class edge1368 implements Comparable<edge1368> {
    int from, to, weight;

    public edge1368(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge1368 e) {
        return this.weight - e.weight;
    }
}