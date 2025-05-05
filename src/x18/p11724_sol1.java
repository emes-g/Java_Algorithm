package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접행렬 BFS
// 모든 정점들에 대해, 각 정점과 연결된 모든 정점을 순회하는데 O(V^2)

public class p11724_sol1 {
    static int n, ans;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
        }
        for (int i = 1; i <= n; i++) {  // O(V^2)
            if (visited[i]) {
                continue;
            }
            bfs(i);
            ans++;
        }
        System.out.println(ans);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);
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
            }
        }
    }
}
