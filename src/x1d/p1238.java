package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 응용, O(ElogE)
// 한 정점에서 다른 모든 정점으로의 최단거리를 구할 때뿐만 아니라,
// 모든 정점에서 한 정점으로의 최단거리를 구할 때도 다익스트라를 사용할 수 있음

public class p1238 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m, x; // n <= 1000, m <= 10000
    static ArrayList<ArrayList<edge1238>> go, back;
    static int[] goDist, backDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        go = new ArrayList<>();
        back = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            go.add(new ArrayList<>());
            back.add(new ArrayList<>());
        }
        goDist = new int[n + 1];
        backDist = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            go.get(v).add(new edge1238(u, weight)); // 역방향 그래프, 간선 방향만 반전
            back.get(u).add(new edge1238(v, weight));
        }
        dijkstra(x, go, goDist);
        dijkstra(x, back, backDist);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, goDist[i] + backDist[i]);
        }
        System.out.println(ans);
    }

    public static void dijkstra(int start, ArrayList<ArrayList<edge1238>> list, int[] dist) {
        Arrays.fill(dist, MAX);
        PriorityQueue<edge1238> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        dist[start] = 0;
        pq.offer(new edge1238(start, dist[start]));
        while (!pq.isEmpty()) {
            edge1238 curr = pq.poll();
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge1238 next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new edge1238(next.to, dist[next.to]));
                }
            }
        }
    }
}

class edge1238 {
    int to, weight;

    public edge1238(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}