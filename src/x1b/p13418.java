package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST, Prim 응용

public class p13418 {
    static int n, m;
    static ArrayList<ArrayList<edge13418>> list = new ArrayList<>();
    static boolean[] visited;
    static PriorityQueue<edge13418> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edge13418(from, to, weight));
            list.get(to).add(new edge13418(to, from, weight));
        }
        long best = prim(0, true);
        long worst = prim(0, false);
        System.out.println(worst - best);
    }

    public static long prim(int start, boolean isBest) {
        visited = new boolean[n + 1];
        if (isBest) {
            pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);    // 내리막길부터 방문
        } else {
            pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);    // 오르막길부터 방문
        }
        visited[start] = true;
        for (edge13418 edge : list.get(start)) {
            pq.offer(edge);
        }
        int cnt = 0;
        long res = 0;
        while (!pq.isEmpty() && cnt != n) { // 정점이 n+1개
            edge13418 curr = pq.poll();
            if (visited[curr.to]) {
                continue;
            }
            visited[curr.to] = true;
            cnt++;
            if (curr.weight == 0) { // 오르막길
                res++;
            }
            for (edge13418 next : list.get(curr.to)) {
                if (visited[next.to]) {
                    continue;
                }
                pq.offer(next);
            }
        }
        return res * res;
    }
}

class edge13418 {
    int from, to, weight;

    public edge13418(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}