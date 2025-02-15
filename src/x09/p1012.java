package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1012 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int t, m, n, k;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());   // 열
            n = Integer.parseInt(st.nextToken());   // 행
            k = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            visited = new boolean[n][m];
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                board[x][y] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int x, int y) {
        Queue<pair1012> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new pair1012(x, y));
        while (!q.isEmpty()) {
            pair1012 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (board[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new pair1012(nx, ny));
            }
        }
    }
}

class pair1012 {
    int x, y;

    public pair1012(int x, int y) {
        this.x = x;
        this.y = y;
    }
}