package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
알고리즘:
1. 벽 3개 만들기 (64C3)
2. 바이러스 확산
3. 안전 영역 갱신

시간복잡도: O((NM)^3 * NM)
- 벽 세우기 경우의 수: nmC3
- BFS: nm
 */

public class p14502 {
    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m, ans, boardSize;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        boardSize = n * m;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(0, 0);
        System.out.println(ans);
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        // 넣는 시점에 방문 표시
        visited[x][y] = true;
        q.offer(new Pair(x, y));
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (board[nx][ny] == WALL || visited[nx][ny]) {
                    continue;
                }
                // 넣는 시점에 방문 표시
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }
    }

    private static void func(int level, int start) {
        if (level == 3) {
            // 2. 바이러스 확산
            visited = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == VIRUS && !visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            // 3. 안전 영역 갱신
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == EMPTY && !visited[i][j]) {
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        if (start >= boardSize) {
            return;
        }
        // 1. 벽 3개 만들기
        int x, y;
        /*
        for (int i=x; i<n; i++) {
            for (int j=y; j<m; j++) { ... }
        }
        위처럼 반복변수(i,j)를 설정하면 0~(y-1)열은 탐색하지 못하게 됨
        -> 1차원 변수(idx)로 관리하면 편리함
         */
        for (int idx = start; idx < boardSize; idx++) {
            x = idx / m;
            y = idx % m;
            if (board[x][y] != EMPTY) {
                continue;
            }
            board[x][y] = WALL;
            func(level + 1, idx + 1);
            board[x][y] = EMPTY;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}