package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p5014 {
    static int[] dx;

    static int f, s, g, u, d;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        dx = new int[]{u, -d};
        dist = new int[f + 1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        dist[s] = 0;
        q.offer(s);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == g) {
                System.out.println(dist[g]);
                return;
            }
            for (int i = 0; i < 2; i++) {
                int nx = curr + dx[i];
                if (nx < 1 || nx > f) {
                    continue;
                }
                if (dist[nx] != -1) {
                    continue;
                }
                dist[nx] = dist[curr] + 1;
                q.offer(nx);
            }
        }
        System.out.println("use the stairs");
    }
}
