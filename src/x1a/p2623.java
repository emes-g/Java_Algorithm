package x1a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// [위상 정렬]
// 문제에서 원소 간 선후 관계가 주어지고, 순서를 정해야 하는 상황일 때

public class p2623 {
    static int n, cnt;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] arr, indegree;
    static ArrayList<Integer> result = new ArrayList<>();   // 위상 정렬 결과

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        indegree = new int[n + 1];
        // 1. indegree 배열 채우기
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            arr = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < cnt - 1; i++) {
                int from = arr[i];
                int to = arr[i + 1];
                list.get(from).add(to);
                indegree[to]++;
            }
        }
        // 2. indegree가 0인 정점들을 담을 큐 선언 및 초기화
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        // 3. 큐에서 뽑아서 위상 정렬 결과에 추가
        // 4. 현재 정점과 연결된 모든 정점의 indegree를 1 감소. 이때 indegree가 0이 되면 큐에 삽입
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
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int node : result) {
            sb.append(node).append('\n');
        }
        System.out.println(sb);
    }
}
