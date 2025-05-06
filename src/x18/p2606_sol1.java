package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 행렬 BFS, O(V^2)

public class p2606_sol1 {
    static int n, ans;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
        }
        bfs(1);
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
                ans++;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
}
