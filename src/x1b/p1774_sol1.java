package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// MST, Kruskal 응용 (일부 정점이 이미 연결되어 있는 경우)
// 이미 연결된 통로 파악을 Union-Find 대신 boolean 배열 활용

public class p1774_sol1 {
    static int n, m, k;
    static pos1774[] pos;
    static edge1774[] edges;
    static boolean[][] preConnected;    // 이미 연결된 통로인지 O(1)에 파악
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = (n * (n - 1)) / 2;
        pos = new pos1774[n + 1];
        edges = new edge1774[k];
        preConnected = new boolean[n + 1][n + 1];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pos[i] = new pos1774(x, y);
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            preConnected[x][y] = preConnected[y][x] = true;
            edges[cnt++] = new edge1774(x, y, 0);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (preConnected[i][j]) {
                    continue;
                }
                pos1774 point1 = pos[i];
                pos1774 point2 = pos[j];
                long xDiff = point2.x - point1.x;
                long yDiff = point2.y - point1.y;
                double weight = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                edges[cnt++] = new edge1774(i, j, weight);
            }
        }
        Arrays.sort(edges);
        cnt = 0;
        double ans = 0;
        for (edge1774 edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            ans += edge.weight;
            if (++cnt == n - 1) {
                break;
            }
        }
        System.out.printf("%.2f\n", ans);
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

class pos1774 {
    int x, y;

    public pos1774(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class edge1774 implements Comparable<edge1774> {
    int from, to;
    double weight;

    public edge1774(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge1774 e) {
        return Double.compare(this.weight, e.weight);
    }
}