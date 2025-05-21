package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리 : 무방향이면서 사이클이 없는 연결 그래프
// 시간복잡도 : O(T * (V+E))
public class p4803 {
    static int n, m;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                System.out.println(sb);
                return;
            }
            list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                list.add(new ArrayList<>());
            }
            visited = new boolean[n + 1];
            while (m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(from).add(to);
                list.get(to).add(from);
            }
            int treeCnt = getTreeCnt(); // O(V+E)
            if (treeCnt == 0) {
                sb.append("Case ").append(t++).append(": No trees.\n");
            } else if (treeCnt == 1) {
                sb.append("Case ").append(t++).append(": There is one tree.\n");
            } else {
                sb.append("Case ").append(t++).append(": A forest of ").append(treeCnt).append(" trees.\n");
            }
        }
    }

    public static int getTreeCnt() {
        int treeCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (!dfs(i, -1)) {   // 사이클을 탐지하지 못한 경우
                    treeCnt++;
                }
            }
        }
        return treeCnt;
    }

    public static boolean dfs(int curr, int p) {
        visited[curr] = true;
        for (int next : list.get(curr)) {
            if (!visited[next]) {
                if (dfs(next, curr)) {
                    return true;
                }
                continue;
            }
            if (next != p) {
                return true;
            }
        }
        return false;
    }
}
