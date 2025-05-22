package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 그래프를 트리로 만들기 위해 필요한 연산 횟수를 구하는 문제
// [방법 2] 제거할 간선의 개수만 구함

public class p20955_sol2 {
    static int n, m, ans;
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
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        int areaCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                areaCnt++;
            }
        }
        ans += (areaCnt - 1);   // 모든 연결요소 연결
        ans += (m + areaCnt - 1 - (n - 1)); // (n-1)개의 간선만 남겨두고 나머지 제거
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