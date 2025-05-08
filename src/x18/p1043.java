package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 행렬 BFS + 구현, O(V^2)

public class p1043 {
    static int n, m; // 사람 수, 파티 수
    static boolean[] knowTruth; // 진실을 알고 있는지 여부
    static int[][] arr;
    static boolean[] visited;
    static ArrayList<HashSet<Integer>> parties = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        knowTruth = new boolean[n + 1];
        arr = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            knowTruth[num] = true;
        }
        for (int i = 0; i < m; i++) {
            parties.add(new HashSet<>());
            HashSet<Integer> party = parties.get(i);
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            party.add(from);
            if (k == 1) {
                continue;
            }
            int to;
            while (st.hasMoreTokens()) {
                to = Integer.parseInt(st.nextToken());
                arr[from][to] = arr[to][from] = 1;
                from = to;
                party.add(to);
            }
        }
        for (int i = 1; i <= n; i++) {  // 모든 정점 방문, O(V^2)
            if (knowTruth[i] && !visited[i]) {
                bfs(i);
            }
        }
        int ans = 0;
        for (HashSet<Integer> party : parties) {    // O(V^2)
            boolean flag = false;
            for (int member : party) {
                if (knowTruth[member]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                knowTruth[next] = true;
                q.offer(next);
            }
        }
    }
}
