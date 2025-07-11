package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 기본, O(ElogE)

public class p1504 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, e;    // n <= 800, e <= 20만
    static ArrayList<ArrayList<edge1504>> list = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(u).add(new edge1504(v, w));
            list.get(v).add(new edge1504(u, w));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int path1 = getPath(v1, v2);
        int path2 = getPath(v2, v1);
        if (path1 == MAX && path2 == MAX) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(path1, path2));
    }

    public static int getPath(int v1, int v2) {
        int[] subPath = new int[3];
        subPath[0] = dijkstra(1, v1);
        subPath[1] = dijkstra(v1, v2);
        subPath[2] = dijkstra(v2, n);
        int path = 0;
        for (int i = 0; i < 3; i++) {
            if (subPath[i] == MAX) {
                return MAX;
            }
            path += subPath[i];
        }
        return path;
    }

    public static int dijkstra(int start, int end) {
        dist = new int[n + 1];
        Arrays.fill(dist, MAX);
        PriorityQueue<edge1504> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        dist[start] = 0;
        pq.offer(new edge1504(start, dist[start]));
        while (!pq.isEmpty()) {
            edge1504 curr = pq.poll();
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge1504 next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new edge1504(next.to, dist[next.to]));
                }
            }
        }
        return dist[end];
    }
}

class edge1504 {
    int to, weight;

    public edge1504(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
