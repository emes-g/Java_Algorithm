package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 재귀 DFS, 인접 리스트 BFS, O(V+E)
// 단순 그래프는 아니다만, 본 문제에서는 고려할 필요 없음

public class p1260_sol1 {
    static int n;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
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

    public static void dfs(int curr) {
        visited[curr] = true;
        sb.append(curr).append(' ');
        for (int next : list.get(curr)) {
            if (visited[next]) {
                continue;
            }
            dfs(next);
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
            for (int next : list.get(curr)) {
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
