package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 인접 리스트 BFS
// 모든 정점에서 모든 정점으로의 거리 측정, O(V^3)

public class p2660 {
    static int n;
    static int[][] arr, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], -1);
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 && to == -1) {
                break;
            }
            arr[from][to] = arr[to][from] = 1;
        }
        // 회장 후보 점수 탐색
        int candidateScore = 50;
        for (int i = 1; i <= n; i++) {  // O(V^3)
            candidateScore = Math.min(candidateScore, bfs(i));
        }
        // 회장 후보 점수를 기반으로, 회장 후보 탐색
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int score = 0;
            for (int j = 1; j <= n; j++) {
                score = Math.max(score, dist[i][j]);
            }
            if (score == candidateScore) {
                candidates.add(i);
            }
        }
        // 회장 후보 출력
        StringBuilder sb = new StringBuilder();
        sb.append(candidateScore).append(' ').append(candidates.size()).append('\n');
        for (int candidate : candidates) {
            sb.append(candidate).append(' ');
        }
        System.out.println(sb);
    }

    public static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        dist[start][start] = 0;
        q.offer(start);
        int score = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            score = dist[start][curr];  // BFS에서의 큐는 거리순이다.
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (dist[start][next] != -1) {
                    continue;
                }
                dist[start][next] = dist[start][curr] + 1;
                q.offer(next);
            }
        }
        return score;
    }
}
