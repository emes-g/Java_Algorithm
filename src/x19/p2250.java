package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 이진 트리의 순회, O(V)
// lc와 rc를 통해 parent 만들고, root 도출

public class p2250 {
    static int n;
    static int[] lc, rc, parent;
    static ArrayList<ArrayList<Integer>> levels = new ArrayList<>();    // 정렬 상태 보장
    static int pos = 1; // 현재 정점의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lc = new int[n + 1];
        rc = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            levels.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            lc[p] = Integer.parseInt(st.nextToken());
            rc[p] = Integer.parseInt(st.nextToken());
            if (lc[p] != -1) {
                parent[lc[p]] = p;
            }
            if (rc[p] != -1) {
                parent[rc[p]] = p;
            }
        }
        // 루트 노드 여부를 제공해주지 않으므로, 직접 찾아야 함
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }
        dfs(1, root);
        // 최대 너비 및 보유 레벨 탐색
        int maxWidth = -1;
        int level = -1;
        for (int i = 1; i <= n; i++) {
            ArrayList<Integer> curr = levels.get(i);
            if (curr.isEmpty()) {
                break;
            }
            int currWidth = curr.get(curr.size() - 1) - curr.get(0);
            if (maxWidth < currWidth) {
                maxWidth = currWidth;
                level = i;
            }
        }
        maxWidth++;
        System.out.printf("%d %d", level, maxWidth);
    }

    // 중위 순회
    public static void dfs(int level, int curr) {
        if (lc[curr] != -1) {
            dfs(level + 1, lc[curr]);
        }
        levels.get(level).add(pos++);
        if (rc[curr] != -1) {
            dfs(level + 1, rc[curr]);
        }
    }
}
