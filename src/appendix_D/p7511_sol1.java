package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// Union-Find 안쓰는 풀이
// 시간복잡도 : O(T * (K + V + E + M))

public class p7511_sol1 {
    static int n, k, m; // n <= 백만, k <= 십만, m <= 십만
    static ArrayList<ArrayList<Integer>> list;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int t = 1;
        StringBuilder sb = new StringBuilder();
        while (t <= T) {
            n = Integer.parseInt(br.readLine());
            k = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            group = new int[n];
            Arrays.fill(group, -1);
            for (int i = 0; i < n; i++) {
                list.add(new ArrayList<>());
            }
            for (int i = 0; i < k; i++) {   // O(K)
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                list.get(from).add(to);
                list.get(to).add(from);
            }
            int groupNum = 0;
            for (int i = 0; i < n; i++) {   // O(V+E)
                if (group[i] == -1) {
                    bfs(i, groupNum++);
                }
            }
            sb.append("Scenario ").append(t++).append(":\n");
            m = Integer.parseInt(br.readLine());
            while (m-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                sb.append(group[u] == group[v] ? 1 : 0).append('\n');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int start, int groupNum) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        group[start] = groupNum;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : list.get(curr)) {
                if (group[next] != -1) {
                    continue;
                }
                group[next] = groupNum;
                q.offer(next);
            }
        }
    }
}
