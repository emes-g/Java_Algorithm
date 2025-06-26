package x1a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 우선순위 큐를 이용한 위상정렬, O(N + MlogN)

public class p1766 {
    static int n, m;    // n <= 32000, m <= 10만
    static int[] indegree;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // 1. indegree 테이블 생성
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            indegree[to]++;
        }
        // 2. indegree가 0인 정점들 우선순위 큐에 넣기
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 오름차순 정렬
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }
        // 3. 큐에서 뽑아서, 위상정렬 결과에 추가
        // 4. 인접한 정점의 indegree 1 감소시키는데, 0이 되었다면 큐에 넣기
        // 5. 3, 4를 큐에 빌때까지 반복
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(' ');
            for (int next : list.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}
