package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 알고리즘, O(V^3)

public class p21940 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n, m, k;    // n <= 200, m <= n * (n-1)
    static int[][] dist;
    static int[] livings;   // 친구들이 살고 있는 도시 번호들
    static int[] times; // i번 도시에서 친구들이 살고 있는 도시들로의 최대 왕복시간

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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dist[from][to] = weight;
        }
        // 모든 정점 쌍 사이의 최단 거리 구하기
        for (int s = 1; s <= n; s++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][s] + dist[s][j]);
                }
            }
        }
        k = Integer.parseInt(br.readLine());
        livings = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            livings[i] = Integer.parseInt(st.nextToken());
        }
        // i번 도시에서 친구들이 살고 있는 도시들로의 최대 왕복시간 구하고, 그 값이 최소가 되는 도시 찾기
        times = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                times[i] = Math.max(times[i], dist[i][livings[j]] + dist[livings[j]][i]);
            }
            ans = Math.min(ans, times[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (ans == times[i]) {
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb);
    }
}
