package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2573 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m, cnt, year;
    static int[][] board, ref;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        ref = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            year++;
            cnt = 0;
            for (int i = 0; i < n; i++) {
                ref[i] = Arrays.copyOf(board[i], board[i].length);
                Arrays.fill(visited[i], false);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] != 0 && !visited[i][j]) {
                        if (++cnt >= 2) {   // 작년에 2개 이상의 덩어리로 분리된 경우라면
                            System.out.println(year - 1);
                            return;
                        }
                        bfs(i, j);
                    }
                }
            }
            if (cnt == 0) {
                System.out.println(0);
                return;
            }
        }
    }

    public static void bfs(int x, int y) {
        Queue<pair2573> q = new LinkedList<>();
        visited[x][y] = true;
        q.offer(new pair2573(x, y));
        while (!q.isEmpty()) {
            pair2573 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (ref[nx][ny] == 0) { // 바닷물에 인접해 있다면
                    board[curr.x][curr.y] = Math.max(0, board[curr.x][curr.y] - 1);
                }
                if (ref[nx][ny] != 0 && !visited[nx][ny]) { // 동일한 덩어리에 속해 있다면
                    visited[nx][ny] = true;
                    q.offer(new pair2573(nx, ny));
                }
            }
        }
    }
}

class pair2573 {
    int x, y;

    public pair2573(int x, int y) {
        this.x = x;
        this.y = y;
    }
}