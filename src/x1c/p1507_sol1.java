package x1c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

// 플로이드 알고리즘 응용, O(V^3)

// 알고리즘
// 1. dist 이용하여 그래프 복원
// 2. 복원한 그래프로 dist 새로 생성
// 3. 두 dist 대조

public class p1507_sol1 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static int n;   // n <= 20
    static int[][] orgDist, newDist;
    static HashSet<edge1507> edges = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        orgDist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                orgDist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1. dist 이용하여 그래프 복원
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                edges.add(new edge1507(i, j, orgDist[i][j]));
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (orgDist[i][j] == orgDist[i][k] + orgDist[k][j]) {
                        edges.remove(new edge1507(i, j, orgDist[i][j]));
                    }
                }
            }
        }
        newDist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(newDist[i], MAX);
            newDist[i][i] = 0;
        }
        for (edge1507 edge : edges) {
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;
            newDist[from][to] = newDist[to][from] = weight;
        }
        // 2. 복원한 그래프로 dist 새로 생성
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    newDist[i][j] = Math.min(newDist[i][j], newDist[i][k] + newDist[k][j]);
                }
            }
        }
        // 3. 두 dist 대조
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (orgDist[i][j] != newDist[i][j]) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        int ans = 0;
        for (edge1507 edge : edges) {
            ans += edge.weight;
        }
        System.out.println(ans);
    }
}

class edge1507 {
    int from, to, weight;

    public edge1507(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof edge1507)) {
            return false;
        }
        edge1507 e = (edge1507) o;
        return Objects.equals(this.from, e.from) && Objects.equals(this.to, e.to) && Objects.equals(this.weight, e.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, weight);
    }
}