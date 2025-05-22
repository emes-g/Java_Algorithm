package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

// 그래프를 트리로 만들기 위해 필요한 연산 횟수를 구하는 문제
// [방법 1] 제거할 간선을 실제로 찾는 방식

public class p20955_sol1 {
    static int n, ans;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static HashSet<pair20955> erased = new HashSet<>();

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
        int areaCnt = 0;    // 사이클이 없는 연결요소 개수
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, -1);
                areaCnt++;
            }
        }
        ans += (areaCnt - 1);   // 모든 연결요소를 연결
        System.out.println(ans);
    }

    public static void dfs(int curr, int p) {
        visited[curr] = true;
        for (int next : list.get(curr)) {
            if (!visited[next]) {
                dfs(next, curr);
                continue;
            }
            if (next != p && !erased.contains(new pair20955(curr, next))) { // 제거한 간선
                ans++;
                erased.add(new pair20955(curr, next));
                erased.add(new pair20955(next, curr));
            }
        }
    }
}

class pair20955 {
    int x, y;

    public pair20955(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof pair20955)) {
            return false;
        }
        pair20955 p = (pair20955) o;
        return Objects.equals(this.x, p.x) && Objects.equals(this.y, p.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}