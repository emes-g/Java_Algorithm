package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 플로이드 알고리즘, O(V^3)

public class p11780 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m;
    static int[][] dist, next;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        next = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if (dist[from][to] > weight) {
                dist[from][to] = weight;
                next[from][to] = to;
            }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(dist[i][j] == MAX ? 0 : dist[i][j]).append(' ');
            }
            sb.append('\n');
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                addPath(i, j);
            }
        }
        System.out.println(sb);
    }

    public static void addPath(int start, int end) {
        if (dist[start][end] == 0 || dist[start][end] == MAX) { // 출발지와 목적지가 동일하거나, 경로가 없다면
            sb.append("0\n");
            return;
        }
        Queue<Integer> q = new LinkedList<>();
        int curr = start;
        while (curr != end) {
            q.offer(curr);
            curr = next[curr][end];
        }
        q.offer(curr);
        sb.append(q.size()).append(' ');
        while (!q.isEmpty()) {
            sb.append(q.poll()).append(' ');
        }
        sb.append('\n');
    }
}
