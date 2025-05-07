package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// [이분 그래프]
// 인접한 정점끼리 서로 다른 색으로 칠해서 모든 정점을 2가지 색으로만 칠할 수 있는 그래프
// 모든 간선을 방문하므로 O(V+E)
// 연결 그래프가 아닌 경우도 고려해야 하며, 무방향 그래프를 전제로 함

public class p1707 {
    static final int NONE = -1;
    static final int RED = 0;
    static final int BLACK = 1;

    static int v;
    static ArrayList<ArrayList<Integer>> list;
    static int[] colors;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                list.add(new ArrayList<>());
            }
            colors = new int[v + 1];
            Arrays.fill(colors, NONE);
            int e = Integer.parseInt(st.nextToken());
            while (e-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(from).add(to);
                list.get(to).add(from);
            }
            boolean flag = false;
            for (int i = 1; i <= v; i++) {
                if (colors[i] != NONE) {
                    continue;
                }
                if (!bfs(i)) {  // 이분 그래프가 아니라면
                    flag = true;
                    break;
                }
            }
            sb.append(flag ? "NO" : "YES").append("\n");
        }
        System.out.println(sb);
    }

    // 이분 그래프 여부 반환
    public static boolean bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        colors[start] = RED;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : list.get(curr)) {
                if (colors[next] == NONE) {
                    colors[next] = (colors[curr] == RED) ? BLACK : RED;
                    q.offer(next);
                    continue;
                }
                if (colors[next] == colors[curr]) { // 인접한 정점이 동일한 색상이라면
                    return false;
                }
            }
        }
        return true;
    }
}
