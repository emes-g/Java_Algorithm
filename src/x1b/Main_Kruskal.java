package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[input]
5 7
1 2 4
1 3 3
1 4 3
2 5 8
3 4 3
3 5 5
4 5 6

[output]
1	3	3
1	4	3
1	2	4
3	5	5
 */

public class Main_Kruskal {
    static int n, m;    // 정점 개수, 간선 개수
    static edgeMain[] edges;
    static int[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edges = new edgeMain[m];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new edgeMain(from, to, weight);
        }
        kruskal();
    }

    public static void kruskal() {
        // 1. 비용 오름차순 정렬
        Arrays.sort(edges);
        // 2. 두 정점이 다른 그룹이면, 같은 그룹으로 편입시키며 현재 간선을 MST에 추가
        // 3. 간선을 n-1개 추가할 때까지 반복
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (edgeMain edge : edges) {
            int from = edge.from;
            int to = edge.to;
            // 두 정점이 같은 그룹이면 아무것도 X
            // 두 정점이 다른 그룹이면, 같은 그룹으로 편입시키며 현재 간선을 MST에 추가
            if (!union(from, to)) {
                continue;
            }
            sb.append(edge).append('\n');
            if (++cnt == n - 1) {
                break;
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
        return true;
    }

    public static int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }
}

class edgeMain implements Comparable<edgeMain> {
    int from, to, weight;

    public edgeMain(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edgeMain e) {
        return this.weight - e.weight;
    }

    @Override
    public String toString() {
        return String.valueOf(from) + '\t' +
                to + '\t' +
                weight;
    }
}

