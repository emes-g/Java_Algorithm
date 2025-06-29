package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// MST, Kruskal, O(ElogE)

public class p1197_sol1 {
    static int v, e, ans, cnt;
    static edge1197[] edges;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        edges = new edge1197[e];
        p = new int[v + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new edge1197(from, to, weight);
        }
        Arrays.sort(edges);
        for (edge1197 edge : edges) {
            if (!union(edge.from, edge.to)) { // 같은 그룹일 경우, 아무것도 X
                continue;
            }
            // 다른 그룹인 경우, 같은 그룹으로 편입하고 MST에 현재 간선 추가
            ans += edge.weight;
            if (++cnt == v - 1) {
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

class edge1197 implements Comparable<edge1197> {
    int from, to, weight;

    public edge1197(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge1197 e) {
        return this.weight - e.weight;
    }
}
