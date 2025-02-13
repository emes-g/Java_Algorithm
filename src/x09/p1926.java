package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1926 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        visited = new boolean[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    cnt++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
    }

    public static int bfs(int x, int y) {
        Queue<pair1926> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new pair1926(x, y));

        int size = 1;
        while (!q.isEmpty()) {
            pair1926 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                if (board[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new pair1926(nx, ny));
                size++;
            }
        }
        return size;
    }
}

class pair1926 {
    int x;
    int y;

    public pair1926(int x, int y) {
        this.x = x;
        this.y = y;
    }
}