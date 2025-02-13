package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2178 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] board;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        dist = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1) - '0';
                dist[i][j] = -1;
            }
        }

        Queue<pair2178> q = new LinkedList<>();
        // 출발점 처리
        dist[1][1] = 1;
        q.offer(new pair2178(1, 1));
        while (!q.isEmpty()) {
            pair2178 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                if (board[nx][ny] == 0 || dist[nx][ny] != -1) {
                    continue;
                }
                // 큐에 삽입할 때 방문 처리
                dist[nx][ny] = dist[curr.x][curr.y] + 1;
                q.offer(new pair2178(nx, ny));
            }
        }
        System.out.println(dist[n][m]);
    }
}

class pair2178 {
    int x;
    int y;

    public pair2178(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
