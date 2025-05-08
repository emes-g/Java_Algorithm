package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 행렬 BFS
// 모든 정점에서 모든 정점으로의 거리 측정, O(V^3)

public class p2617 {
    static int n, ans;
    static int[][] arr1, dist1;
    static int[][] arr2, dist2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr1 = new int[n + 1][n + 1];
        arr2 = new int[n + 1][n + 1];
        dist1 = new int[n + 1][n + 1];
        dist2 = new int[n + 1][n + 1];
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr1[from][to] = arr2[to][from] = 1;
        }
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist1[i], -1);
            Arrays.fill(dist2[i], -1);
        }
        for (int i = 1; i <= n; i++) {  // O(V^3)
            bfs(arr1, dist1, i);
            bfs(arr2, dist2, i);
        }
        int mid = (n + 1) / 2;
        for (int i = 1; i <= n; i++) {  // O(V^2)
            int cnt1 = getCnt(dist1, i);
            int cnt2 = getCnt(dist2, i);
            if (cnt1 >= mid || cnt2 >= mid) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void bfs(int[][] arr, int[][] dist, int start) {
        Queue<Integer> q = new LinkedList<>();
        dist[start][start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (dist[start][next] != -1) {
                    continue;
                }
                dist[start][next] = dist[start][curr] + 1;
                q.offer(next);
            }
        }
    }

    // num번 노드에서 뻗어나갈 수 있는 갈래의 수
    public static int getCnt(int[][] dist, int num) {
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[num][i] > 0) {
                cnt++;
            }
        }
        return cnt;
    }
}