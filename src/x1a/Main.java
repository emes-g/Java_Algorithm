package x1a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
7 7
1 2
3 2
3 4
4 2
4 5
5 6
7 5
 */

public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] indegree;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        indegree = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // 1. indegree 배열 채우기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            indegree[to]++;
        }
        // 2. indegree가 0인 정점들 큐에 담기 (초기값 세팅)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        // 3. 큐에서 정점 꺼내서 위상 정렬 결과에 추가
        // 4. 해당 정점과 연결된 모든 정점의 indegree 1 감소, 만약 indegree가 0이면 큐에 추가
        // 5. 3과 4를 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int curr = q.poll();
            result.add(curr);
            for (int next : list.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        // 결과 출력
        if (result.size() != n) {
            System.out.println("사이클 감지!");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int node : result) {
            sb.append(node).append(' ');
        }
        System.out.println(sb);
    }
}
