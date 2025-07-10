package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 플로이드 알고리즘 응용, O(V^3)

// 기존 내 풀이와 달리 dist를 새로 만들지 않고,
// 모순이 발생하는 dist인지 파악하고, 직행이 아닌 간선을 분류하는 방식

public class p1507_sol2 {
    static int n;
    static int[][] dist;
    static boolean[][] isDirect;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1][n + 1];
        isDirect = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                isDirect[i][j] = true;
            }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (k == i || k == j || i == j) {
                        continue;
                    }
                    // 강호는 모든 쌍의 도시에 대해서 최소 이동시간을 구해놓았다고 했는데,
                    // 최소 이동시간이 아닌 곳이 존재 (모순 발생)
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }
                    // i번 도시와 j번 도시가 다이렉트로 연결되지 않은 경우
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        isDirect[i][j] = false;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (isDirect[i][j]) {
                    ans += dist[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
