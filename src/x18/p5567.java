package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 리스트 BFS, V^2에 비해 E가 작음 + 거리 측정
// 상근이와 거리가 2 이내인 동기들만 초대

public class p5567 {
    static int n, ans;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        bfs(1);
        for (int val : dist) {
            if (val == 1 || val == 2) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        dist[start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (dist[curr] > 2) {
                continue;
            }
            for (int next : list.get(curr)) {
                if (dist[next] != -1) {
                    continue;
                }
                dist[next] = dist[curr] + 1;
                q.offer(next);
            }
        }
    }
}
