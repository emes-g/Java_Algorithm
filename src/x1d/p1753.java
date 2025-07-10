package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 기본, O(ElogE)

public class p1753 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int v, e;    // v <= 2만, e <= 30만
    static ArrayList<ArrayList<edge1753>> list = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edge1753(to, weight));
        }
        dijkstra(k);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++) {
            sb.append(dist[i] == MAX ? "INF" : dist[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        dist = new int[v + 1];
        Arrays.fill(dist, MAX);
        PriorityQueue<edge1753> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new edge1753(start, dist[start]));
        while (!pq.isEmpty()) {
            edge1753 curr = pq.poll();
            // 유효한 간선이 아니라면
            // (현재 dist 값으로 갱신하기 위해 사용했던 간선이 아니라면)
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge1753 next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {  // 유효한 간선이라면
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new edge1753(next.to, dist[next.to]));
                }
            }
        }
    }
}

class edge1753 implements Comparable<edge1753> {
    int to, weight;

    public edge1753(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge1753 e) {
        return this.weight - e.weight;
    }
}