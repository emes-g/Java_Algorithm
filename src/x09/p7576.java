package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p7576 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] board;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());   // 열
        n = Integer.parseInt(st.nextToken());   // 행
        board = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1];
        Queue<pair7576> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
                // 출발점 처리
                if (board[i][j] == 1) {
                    // 큐에 넣는 시점에 방문 표시
                    dist[i][j] = 0;
                    q.offer(new pair7576(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            pair7576 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                if (board[nx][ny] == -1 || dist[nx][ny] != -1) {
                    continue;
                }
                dist[nx][ny] = dist[curr.x][curr.y] + 1;
                q.offer(new pair7576(nx, ny));
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] != -1 && dist[i][j] == -1) {
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, dist[i][j]);
            }
        }
        System.out.println(max);
    }
}

class pair7576 {
    int x, y;

    public pair7576(int x, int y) {
        this.x = x;
        this.y = y;
    }
}