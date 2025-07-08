package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 플로이드 알고리즘, O(V^3)

public class p1956 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int v, e;    // v <= 400
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        dist = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dist[from][to] = weight;
        }
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (i == j) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int ans = MAX;
        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                ans = Math.min(ans, dist[i][j] + dist[j][i]);
            }
        }
        System.out.println(ans == MAX ? -1 : ans);
    }
}
