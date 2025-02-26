package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p13549 {
    static final int[] dx = {-1, 1};

    static int n, k;
    static int[] dist = new int[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        dist[n] = 0;
        q.offer(n);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == k) {
                System.out.println(dist[k]);
                return;
            }
            int jump = curr * 2;
            while (jump < dist.length && dist[jump] == -1) {
                dist[jump] = dist[curr];
                q.offer(jump);
            }
            for (int i = 0; i < 2; i++) {
                int nx = curr + dx[i];
                if (nx < 0 || nx >= dist.length) {
                    continue;
                }
                if (dist[nx] != -1) {
                    continue;
                }
                dist[nx] = dist[curr] + 1;
                q.offer(nx);
            }
        }
    }
}
