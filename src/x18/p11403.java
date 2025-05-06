package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 행렬 BFS, 거리 측정
// O(V^3)

public class p11403 {
    static int n;
    static int[][] arr, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], -1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= n; i++) {
            bfs(i); // O(V^2)
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] > 0 ? 1 : 0).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        dist[start][start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (dist[start][next] > 0) {    // 출발점으로 돌아오는 경로를 배제하지 않음
                    continue;
                }
                dist[start][next] = dist[start][curr] + 1;
                q.offer(next);
            }
        }
    }
}
