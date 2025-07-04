package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// MST, Prim 응용
// 모든 정점을 고려하지만, 모든 정점이 연결될 필요는 없음 (신장트리 정의 응용)
// 시작점이 여러 개인 BFS 풀듯이 풀면 됨

public class p10423 {
    static int n, m, k;
    static ArrayList<ArrayList<edge10423>> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Queue<Integer> starts = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            starts.offer(Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edge10423(from, to, weight));
            list.get(to).add(new edge10423(to, from, weight));
        }
        System.out.println(prim(starts));
    }

    public static int prim(Queue<Integer> starts) {
        PriorityQueue<edge10423> pq = new PriorityQueue<>();
        int cnt = 0;    // 방문한 노드 개수
        while (!starts.isEmpty()) {
            int start = starts.poll();
            visited[start] = true;
            cnt++;
            for (edge10423 edge : list.get(start)) {
                pq.offer(edge);
            }
        }
        int ans = 0;    // 최소 비용
        while (!pq.isEmpty() && cnt != n) {
            edge10423 curr = pq.poll();
            if (visited[curr.to]) {
                continue;
            }
            visited[curr.to] = true;
            cnt++;
            ans += curr.weight;
            for (edge10423 next : list.get(curr.to)) {
                if (visited[next.to]) {
                    continue;
                }
                pq.offer(next);
            }
        }
        return ans;
    }
}

class edge10423 implements Comparable<edge10423> {
    int from, to, weight;

    public edge10423(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(edge10423 e) {
        return this.weight - e.weight;
    }
}