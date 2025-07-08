package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 알고리즘 응용, O(V^3)
// 두 정점 간의 거리(weight)가 주어지지 않는 대신,
// 두 정점 간의 양방향 통행 여부(b)가 주어짐
// 그래서 거리 대신 양방향 통행 여부를 저장하여 플로이드 알고리즘을 진행

// 근거
// 1. 출발지와 도착지를 입력하면 도착지까지 가기 위해 최소 몇 개의 길을 양방향으로 바꿔야만 하는지를 출력해야 함
// 2. 어떤 두 건물 사이를 잇는 길은 최대 한 개이다.

public class p11562 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m;    // n <= 250
    static int[][] dist;    // 바꿔야 할 일방통행 길의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[u][v] = 0;
            dist[v][u] = (b == 1) ? 0 : 1;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(dist[start][end]).append('\n');
        }
        System.out.println(sb);
    }
}
