package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
[input]
5 7
1 2 4
1 3 3
1 4 3
2 5 8
3 4 3
3 5 5
4 5 6

[output when starts from 1]
1	3	3
1	4	3
1	2	4
3	5	5
 */

public class Main_Prim {
    static int n, m;
    static ArrayList<ArrayList<edgeMain>> list = new ArrayList<>();
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list.get(from).add(new edgeMain(from, to, weight));
            list.get(to).add(new edgeMain(to, from, weight));
        }
        prim(1);
    }

    public static void prim(int start) {    // 1. 시작 정점 지정
        PriorityQueue<edgeMain> pq = new PriorityQueue<>();
        visit[start] = true;
        for (edgeMain edge : list.get(start)) {
            pq.offer(edge);
        }
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        // 2. 우선순위 큐에서 간선을 하나 뽑음
        //  MST에 포함된 두 정점을 연결하는 간선이라면, 아무것도 X
        //  아니라면, 해당 정점과 간선은 MST에 추가하고, 인접한 간선들은 우선순위 큐에 추가하는데,
        //  이때 MST에 포함되지 않은 정점을 연결하는 간선들만 우선순위 큐에 추가해야 함
        // 3. 간선을 n-1개 추가할 때까지 반복
        while (!pq.isEmpty() && cnt != n - 1) {
            edgeMain curr = pq.poll();
            if (visit[curr.to]) {
                continue;
            }
            cnt++;
            sb.append(curr).append('\n');
            // 비재귀 DFS와 동일하게 뽑는 시점에 방문 표시하기 때문에, (넣는 시점에 동일 노드가 여러 번 들어갈 수 있음)
            // 큐에 삽입할때랑, 큐에서 뽑을때 모두 방문 여부를 체크해야함
            visit[curr.to] = true;
            for (edgeMain edge : list.get(curr.to)) {
                if (visit[edge.to]) {
                    continue;
                }
                pq.offer(edge);
            }
        }
        System.out.println(sb);
    }
}
