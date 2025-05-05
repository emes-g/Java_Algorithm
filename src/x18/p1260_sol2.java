package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 비재귀 DFS, 인접 행렬 BFS, O(V^2)
// 단순 그래프는 아니다만, 본 문제에서는 고려할 필요 없음

public class p1260_sol2 {
    static int n;
    static int[][] arr;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
            list.get(from).add(to);
            list.get(to).add(from);
        }
        for (int i = 0; i <= n; i++) {
            Collections.sort(list.get(i));
        }
        dfs(start);
        sb.append('\n');
        bfs(start);
        System.out.println(sb);
    }

    public static void dfs(int start) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            sb.append(curr).append(' ');
            // 비재귀 DFS의 경우, 역순으로 조회해야 재귀 DFS와 동일한 순서 보장
            ArrayList<Integer> neighbors = list.get(curr);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int next = neighbors.get(i);
                if (visited[next]) {
                    continue;
                }
                s.push(next);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visited, false);
        visited[start] = true;
        q.offer(start);
        sb.append(start).append(' ');
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                q.offer(next);
                sb.append(next).append(' ');
            }
        }
        sb.append('\n');
    }
}
