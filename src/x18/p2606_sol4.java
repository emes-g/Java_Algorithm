package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

// 비재귀 DFS, O(V+E)
// 뽑는 시점에 방문 표시

public class p2606_sol4 {
    static int n, ans;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        dfs(1);
        ans--;
        System.out.println(ans);
    }

    public static void dfs(int start) {
        Stack<Integer> s = new Stack<>();
        s.push(start);
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (visited[curr]) {
                continue;
            }
            ans++;
            visited[curr] = true;
            for (int next : list.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                s.push(next);
            }
        }
    }
}
