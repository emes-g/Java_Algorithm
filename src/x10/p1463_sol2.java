package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p1463_sol2 {
    static int n;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        bfs(1);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        dist[start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == n) {
                System.out.println(dist[n]);
                return;
            }
            for (int next : new int[]{curr + 1, curr * 2, curr * 3}) {
                if (next > n) {
                    break;
                }
                if (dist[next] == 0) {
                    dist[next] = dist[curr] + 1;
                    q.offer(next);
                }
            }
        }
    }
}
