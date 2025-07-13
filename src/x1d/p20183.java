package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 응용
// 다익스트라 알고리즘 + 매개변수 탐색
// 시간복잡도 : O(ElogE) * O(log(10억))

public class p20183 {
    static final long MAX = Long.MAX_VALUE / 2;
    static int n, m, a, b;  // n <= 10만, m <= 50만
    static int maxWeight;   // 수금액이 가장 큰 골목의 수금액 <= 10억
    static long c;
    static ArrayList<ArrayList<edge20183>> list = new ArrayList<>();
    static long[] dist; // (출발점, i번 교차로) 경로의 골목별 요금의 최댓값, 즉 요금 상한 중 최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Long.parseLong(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list.get(u).add(new edge20183(v, w));
            list.get(v).add(new edge20183(u, w));
            maxWeight = Math.max(w, maxWeight);
        }
        if (!solve(maxWeight)) {
            System.out.println(-1);
            return;
        }
        int start = 1;
        int end = maxWeight;
        while (start < end) {   // O(solve) * O(maxWeight)
            int mid = (start + end) / 2;
            if (solve(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(end);
    }

    public static boolean solve(int mid) {
        return dijkstra(mid) <= c;
    }

    public static long dijkstra(int limit) {
        dist = new long[n + 1];
        Arrays.fill(dist, MAX);
        PriorityQueue<edge20183> pq = new PriorityQueue<>();
        dist[a] = 0;
        pq.offer(new edge20183(a, dist[a]));
        while (!pq.isEmpty()) {
            edge20183 curr = pq.poll();
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge20183 next : list.get(curr.to)) {
                if (next.weight > limit) {  // 이용 불가능한 간선 배제
                    continue;
                }
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    pq.offer(new edge20183(next.to, dist[next.to]));
                }
            }
        }
        return dist[b];
    }
}

class edge20183 implements Comparable<edge20183> {
    int to;
    long weight;

    public edge20183(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge20183 e) {
        return Long.compare(this.weight, e.weight);
    }
}
