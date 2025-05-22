package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 가중치 트리의 순회, O(N^2 + NM)
// LCA 알고리즘을 통해 개선 가능

public class p1240 {
    static int n;
    static int root = 1;
    static int[][] arr;
    static int[] parent;
    static int[] dist;  // 루트로부터의 거리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];
        parent = new int[n + 1];
        dist = new int[n + 1];
        int m = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            arr[from][to] = d;
            arr[to][from] = d;
        }
        dfs(root);
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int commonAncestor = findCommonAncestor(x, y);
            sb.append(dist[x] + dist[y] - dist[commonAncestor] * 2).append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int curr) {
        for (int next = 1; next <= n; next++) {
            if (arr[curr][next] == 0) {
                continue;
            }
            if (parent[curr] == next) {
                continue;
            }
            parent[next] = curr;
            dist[next] = dist[curr] + arr[curr][next];
            dfs(next);
        }
    }

    public static int findCommonAncestor(int x, int y) {
        ArrayList<Integer> xPath = buildPath(x);
        ArrayList<Integer> yPath = buildPath(y);
        int maxIdx = Integer.min(xPath.size(), yPath.size());
        for (int i = 1; i < maxIdx; i++) {
            if (!xPath.get(i).equals(yPath.get(i))) {
                return xPath.get(i - 1);
            }
        }
        return xPath.get(maxIdx - 1);
    }

    public static ArrayList<Integer> buildPath(int num) {
        ArrayList<Integer> path = new ArrayList<>();
        int curr = num;
        while (curr != root) {
            path.add(curr);
            curr = parent[curr];
        }
        path.add(root);
        Collections.reverse(path);
        return path;
    }
}
