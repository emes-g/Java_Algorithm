package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// MST, Prim, O(eloge)

public class p1197_sol2 {
    static int v, e, ans, cnt;
    static ArrayList<ArrayList<edge1197>> list = new ArrayList<>();
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        visit = new boolean[v + 1];
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edge1197(from, to, weight));
            list.get(to).add(new edge1197(to, from, weight));
        }
        prim(1);
        System.out.println(ans);
    }

    public static void prim(int start) {
        PriorityQueue<edge1197> pq = new PriorityQueue<>();
        visit[start] = true;
        for (edge1197 edge : list.get(start)) {
            pq.offer(edge);
        }
        while (!pq.isEmpty() && cnt != v - 1) {
            edge1197 curr = pq.poll();
            if (visit[curr.to]) {   // 이미 MST에 포함된 정점이라면, 아무것도 X
                continue;
            }
            // MST에 포함되지 않은 정점이라면, 해당 정점과 간선을 MST에 추가
            visit[curr.to] = true;  // 뽑는 시점에 방문 표시
            cnt++;
            ans += curr.weight;
            // 이후 해당 정점의 outdegree 간선(next) 중,
            // MST에 포함되지 않은 정점을 연결하는 간선은 우선순위 큐에 추가
            for (edge1197 next : list.get(curr.to)) {
                if (visit[next.to]) {
                    continue;
                }
                pq.offer(next);
            }
        }
    }
}
