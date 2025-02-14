package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1697 {
    static final int[] dx = {1, -1};

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
                System.out.println(dist[curr]);
                break;
            }
            for (int i = 0; i < 3; i++) {
                int nx = i == 2 ? curr * 2 : curr + dx[i];
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
