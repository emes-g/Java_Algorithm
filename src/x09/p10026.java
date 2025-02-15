package x09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p10026 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n;
    static char[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n + 1][n + 1];
        visited = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= n; j++) {
                board[i][j] = str.charAt(j - 1);
            }
        }

        // 비적록색약에 대해 먼저 BFS
        int[] cnt = new int[2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, board[i][j]);
                    cnt[0]++;
                }
            }
        }

        // 이후, 적록색약에 BFS
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                visited[i][j] = false;
                if (board[i][j] == 'G') {
                    board[i][j] = 'R';
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, board[i][j]);
                    cnt[1]++;
                }
            }
        }

        System.out.printf("%d %d", cnt[0], cnt[1]);
    }

    public static void bfs(int x, int y, char c) {
        Queue<pair10026> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new pair10026(x, y));
        while (!q.isEmpty()) {
            pair10026 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > n) {
                    continue;
                }
                if (board[nx][ny] != c || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new pair10026(nx, ny));
            }
        }
    }
}

class pair10026 {
    int x, y;

    public pair10026(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
