package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2206 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] board;
    static int[][][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1][2];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1) - '0';  // board 타입이 정수형이므로
                dist[i][j][0] = dist[i][j][1] = -1;
            }
        }

        Queue<tuple2206> q = new LinkedList<>();
        dist[1][1][0] = 1;
        q.offer(new tuple2206(1, 1, 0));
        while (!q.isEmpty()) {
            tuple2206 curr = q.poll();
            if (curr.x == n && curr.y == m) {
                System.out.println(dist[curr.x][curr.y][curr.cnt]);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                if (curr.cnt == 0 && board[nx][ny] == 1 && dist[nx][ny][1] == -1) { // 벽을 부수는 경우
                    dist[nx][ny][1] = dist[curr.x][curr.y][0] + 1;
                    q.offer(new tuple2206(nx, ny, 1));
                }
                if (board[nx][ny] == 1 || dist[nx][ny][curr.cnt] != -1) {
                    continue;
                }
                dist[nx][ny][curr.cnt] = dist[curr.x][curr.y][curr.cnt] + 1;
                q.offer(new tuple2206(nx, ny, curr.cnt));
            }
        }
        System.out.println(-1);
    }
}

class tuple2206 {
    int x, y, cnt;

    public tuple2206(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}