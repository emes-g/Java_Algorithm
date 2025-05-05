package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 재귀 DFS

public class p11724_sol3 {
    static int n, ans;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        int m = Integer.parseInt(st.nextToken());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        for (int i = 1; i <= n; i++) {  // O(V+E)
            if (visited[i]) {
                continue;
            }
            dfs(i);
            ans++;
        }
        System.out.println(ans);
    }

    public static void dfs(int curr) {
        visited[curr] = true;
        for (int next : list.get(curr)) {
            if (visited[next]) {
                continue;
            }
            dfs(next);
        }
    }
}
