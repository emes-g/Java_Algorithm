package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 응용
// 역방향 그래프 + 시작점이 여러 개인 다익스트라

public class p17835 {
    static final long MAX = Long.MAX_VALUE / 2;
    static int n, m, k; // n <= 10만, m <= 50만, k <= n
    static ArrayList<ArrayList<edge17835>> list = new ArrayList<>();    // 역방향 그래프
    static long[] dist;
    static int[] starts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(v).add(new edge17835(u, weight));
        }
        st = new StringTokenizer(br.readLine());
        starts = new int[k];
        for (int i = 0; i < k; i++) {
            starts[i] = Integer.parseInt(st.nextToken());
        }
        dijkstra();
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }
        for (int i = 1; i <= n; i++) {
            if (ans == dist[i]) {
                System.out.println(i);
                System.out.println(dist[i]);
                return;
            }
        }
    }

    public static void dijkstra() {
        dist = new long[n + 1];
        Arrays.fill(dist, MAX);
        PriorityQueue<edge17835> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.weight, o2.weight));
        for (int start : starts) {
            dist[start] = 0;
            pq.offer(new edge17835(start, dist[start]));
        }
        while (!pq.isEmpty()) {
            edge17835 curr = pq.poll();
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge17835 next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new edge17835(next.to, dist[next.to]));
                }
            }
        }
    }
}

class edge17835 {
    int to;
    long weight;

    public edge17835(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}