package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7562 {
    static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    static int l, x, y, n, m;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            l = Integer.parseInt(br.readLine());
            dist = new int[l][l];
            for (int i = 0; i < l; i++) {
                Arrays.fill(dist[i], -1);
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            Queue<pair7562> q = new LinkedList<>();
            dist[x][y] = 0;
            q.offer(new pair7562(x, y));
            while (!q.isEmpty()) {
                pair7562 curr = q.poll();
                for (int i = 0; i < 8; i++) {
                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];
                    if (nx < 0 || nx >= l || ny < 0 || ny >= l) {
                        continue;
                    }
                    if (dist[nx][ny] != -1) {
                        continue;
                    }
                    dist[nx][ny] = dist[curr.x][curr.y] + 1;
                    if (nx == n && ny == m) {
                        q.clear();
                        break;
                    }
                    q.offer(new pair7562(nx, ny));
                }
            }
            sb.append(dist[n][m]).append('\n');
        }
        System.out.println(sb);
    }
}

class pair7562 {
    int x, y;

    public pair7562(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
