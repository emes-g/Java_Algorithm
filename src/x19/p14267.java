package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 가중치 트리의 순회 (부모가 주어진 경우), O(V+E) == O(V)
// 한 직원이 칭찬을 여러 번 받을 수 있음에 주의

public class p14267 {
    static final int NOT_VISITED = -1;
    static final int ROOT = 1;

    static int n, m;
    static ArrayList<HashMap<Integer, Integer>> list = new ArrayList<>();
    static int[] parent, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new HashMap<>());
        }
        parent = new int[n + 1];
        dist = new int[n + 1];
        Arrays.fill(dist, NOT_VISITED);
        dist[ROOT] = 0;
        st = new StringTokenizer(br.readLine());
        parent[ROOT] = Integer.parseInt(st.nextToken());
        for (int c = 2; c <= n; c++) {
            int p = Integer.parseInt(st.nextToken());
            parent[c] = p;
            list.get(c).put(p, 0);
            list.get(p).put(c, 0);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            int p = parent[c];
            // 한 직원이 칭찬을 여러 번 받을 수 있음
            list.get(c).put(p, list.get(c).get(p) + dist);
            list.get(p).put(c, list.get(p).get(c) + dist);
        }
        dfs(ROOT);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dist[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void dfs(int curr) {  // O(V+E) == O(V)
        for (int next : list.get(curr).keySet()) {
            if (dist[next] != NOT_VISITED) {
                continue;
            }
            dist[next] = dist[curr] + list.get(curr).get(next);
            dfs(next);
        }
    }
}