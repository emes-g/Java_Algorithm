package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
# TC1
7 10
1 1 1 0 1 0 0 0 0 0
1 0 0 0 1 0 0 0 0 0
1 1 1 0 1 0 0 0 0 0
1 1 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
 */

public class BFS {
    // 아래, 오른쪽, 위, 왼쪽 (6시부터 반시계 방향)
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

        Queue<pairBFS> q = new LinkedList<>();
        // 출발점 처리
        visited[1][1] = true;
        q.offer(new pairBFS(1, 1));
        while (!q.isEmpty()) {
            pairBFS curr = q.poll();
            System.out.printf("curr: (%d, %d)\n", curr.x, curr.y);
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > m || ny < 1 || ny > n) {
                    continue;
                }
                if (visited[nx][ny] || board[nx][ny] == 0) {
                    continue;
                }
                // 큐에 넣는 시점에 방문 표시 (빼낼 때 방문 표시 X)
                visited[nx][ny] = true;
                q.offer(new pairBFS(nx, ny));
            }
        }
    }
}

class pairBFS {
    int x;
    int y;

    public pairBFS(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
