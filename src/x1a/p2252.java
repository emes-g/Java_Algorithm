package x1a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// [위상 정렬]
// 문제에서 원소 간의 선후 관계가 주어지고, 순서를 정해야 하는 상황일 때
// 시간 복잡도 : O(N+M)

public class p2252 {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] indegree;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // 1. indegree 배열 채우기
        indegree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            indegree[to]++;
        }
        // 2. indegree가 0인 정점들을 담는 큐 선언 및 초기화
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        // 3. 큐에서 정점 하나 뽑아서 위상 정렬 결과에 추가
        // 4. 이웃 정점의 indegree를 1 줄이기, 이때 indegree가 0이 되면 해당 정점 큐에 삽입
        // 5. 큐가 빌 때까지 3, 4 반복
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append(' ');
            for (int next : list.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}
