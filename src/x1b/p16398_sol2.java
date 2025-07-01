package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST, Prim

public class p16398_sol2 {
    static int n;
    static ArrayList<ArrayList<edge16398>> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[n];
        for (int from = 0; from < n; from++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int to = 0; to < n; to++) {
                int weight = Integer.parseInt(st.nextToken());
                if (from < to) {
                    list.get(from).add(new edge16398(from, to, weight));
                    list.get(to).add(new edge16398(to, from, weight));
                }
            }
        }
        System.out.println(prim(0));
    }

    public static long prim(int start) {
        PriorityQueue<edge16398> pq = new PriorityQueue<>();
        visited[start] = true;
        for (edge16398 edge : list.get(start)) {
            pq.offer(edge);
        }
        long ans = 0;
        int cnt = 0;
        while (!pq.isEmpty() && cnt != n - 1) {
            edge16398 curr = pq.poll();
            if (visited[curr.to]) {
                continue;
            }
            visited[curr.to] = true;
            cnt++;
            ans += curr.weight;
            for (edge16398 next : list.get(curr.to)) {
                if (visited[next.to]) {
                    continue;
                }
                pq.offer(next);
            }
        }
        return ans;
    }
}
