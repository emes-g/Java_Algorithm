package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

// 다익스트라 알고리즘 경로복원 기본, O(ElogE)

public class p11779 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m;    // n <= 1000, m <= 10만
    static ArrayList<ArrayList<edge11779>> list = new ArrayList<>();
    static int[] dist, prev;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edge11779(to, weight));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        dijkstra(start);
        System.out.println(dist[end]);
        showPath(start, end);
    }

    public static void showPath(int start, int end) {
        Stack<Integer> s = new Stack<>();
        int curr = end;
        while (curr != start) {
            s.push(curr);
            curr = prev[curr];
        }
        s.push(curr);   // curr == start
        StringBuilder sb = new StringBuilder();
        sb.append(s.size()).append('\n');
        while (!s.isEmpty()) {
            sb.append(s.pop()).append(' ');
        }
        System.out.println(sb);
    }

    public static void dijkstra(int start) {
        dist = new int[n + 1];
        Arrays.fill(dist, MAX);
        prev = new int[n + 1];
        PriorityQueue<edge11779> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new edge11779(start, dist[start]));
        while (!pq.isEmpty()) {
            edge11779 curr = pq.poll();
            // 유효한 간선이 아니라면
            // (현재 dist 값으로 갱신하기 위해 사용했던 간선이 아니라면)
            if (dist[curr.to] != curr.weight) {
                continue;
            }
            for (edge11779 next : list.get(curr.to)) {
                if (dist[next.to] > dist[curr.to] + next.weight) {
                    dist[next.to] = dist[curr.to] + next.weight;
                    prev[next.to] = curr.to;
                    pq.offer(new edge11779(next.to, dist[next.to]));
                }
            }
        }
    }
}

class edge11779 implements Comparable<edge11779> {
    int to, weight;

    public edge11779(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge11779 e) {
        return this.weight - e.weight;
    }
}