package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7569 {
    static final int[] dx = {1, 0, -1, 0, 0, 0};
    static final int[] dy = {0, 1, 0, -1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, 1, -1};

    static int m, n, h;
    static int[][][] board, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 열
        n = Integer.parseInt(st.nextToken());   // 행
        h = Integer.parseInt(st.nextToken());   // 높이
        board = new int[n][m][h];
        dist = new int[n][m][h];
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    board[i][j][k] = Integer.parseInt(st.nextToken());
                    dist[i][j][k] = -1;
                }
            }
        }

        Queue<tuple7569> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < h; k++) {
                    if (board[i][j][k] == 1) {
                        dist[i][j][k] = 0;
                        q.offer(new tuple7569(i, j, k));
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            tuple7569 curr = q.poll();
            for (int i = 0; i < 6; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                int nz = curr.z + dz[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || nz < 0 || nz >= h) {
                    continue;
                }
                if (board[nx][ny][nz] == -1 || dist[nx][ny][nz] != -1) {
                    continue;
                }
                dist[nx][ny][nz] = dist[curr.x][curr.y][curr.z] + 1;
                q.offer(new tuple7569(nx, ny, nz));
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < h; k++) {
                    if (board[i][j][k] == 0 && dist[i][j][k] == -1) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, dist[i][j][k]);
                }
            }
        }
        System.out.println(max);
    }
}

class tuple7569 {
    int x, y, z;

    public tuple7569(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
