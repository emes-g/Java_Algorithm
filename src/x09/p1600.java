package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1600 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int[] jx = {2, 1, -1, -2, -2, -1, 1, 2};
    static final int[] jy = {1, 2, 2, 1, -1, -2, -2, -1};

    static int k, w, h;
    static int[][] board;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h][w];
        dist = new int[h][w][k + 1];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                for (int s = 0; s <= k; s++) {
                    dist[i][j][s] = -1;
                }
            }
        }

        Queue<tuple1600> q = new LinkedList<>();
        dist[0][0][0] = 0;
        q.offer(new tuple1600(0, 0, 0));
        while (!q.isEmpty()) {
            tuple1600 curr = q.poll();
            if (curr.x == h - 1 && curr.y == w - 1) {
                System.out.println(dist[curr.x][curr.y][curr.cnt]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }
                if (board[nx][ny] == 1 || dist[nx][ny][curr.cnt] != -1) {
                    continue;
                }
                dist[nx][ny][curr.cnt] = dist[curr.x][curr.y][curr.cnt] + 1;
                q.offer(new tuple1600(nx, ny, curr.cnt));
            }
            if (curr.cnt >= k) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int nx = curr.x + jx[i];
                int ny = curr.y + jy[i];
                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }
                if (board[nx][ny] == 1 || dist[nx][ny][curr.cnt + 1] != -1) {
                    continue;
                }
                dist[nx][ny][curr.cnt + 1] = dist[curr.x][curr.y][curr.cnt] + 1;
                q.offer(new tuple1600(nx, ny, curr.cnt + 1));
            }
        }
        System.out.println(-1);
    }
}

class tuple1600 {
    int x, y, cnt;

    public tuple1600(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
