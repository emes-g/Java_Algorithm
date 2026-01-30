package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
탐색 구현 문제 (BFS는 아님)
- 실수할 확률이 적은 풀이 (실수하더라도 디버깅에 유리한 풀이)

시간복잡도: 대략 O(16NM)
- 각 칸에서 취할 수 있는 방향이 4개고,
- 각 방향에서 인접한 모든 칸을 확인하기 위해서 4번 봐야하기 때문에 대략적으로.
 */

public class p14503 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
    static final int DIRTY = 0, WALL = 1, CLEAN = 2;

    static int n, m, ans, x, y, dir;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 로봇 청소기 탐색
        while (true) {
            // 1. 현재 칸
            if (board[x][y] == DIRTY) {
                board[x][y] = CLEAN;
                ans++;
            }
            // 2. 인접 칸이 깨끗한 경우
            if (good()) {
                if (!canGoBack()) {    // 후진할 수 없는 경우
                    System.out.println(ans);
                    return;
                }
                goBack();
                continue;
            }
            // 3. 인접 칸이 더러운 경우
            rotate();
            if (isDirtyFront()) {
                goFront();
                continue;
            }
        }
    }

    public static void goFront() {
        switch (dir) {
            case EAST:
                y++;
                return;
            case WEST:
                y--;
                return;
            case SOUTH:
                x++;
                return;
            case NORTH:
                x--;
                return;
        }
    }

    public static boolean isDirtyFront() {
        switch (dir) {
            case EAST:
                return board[x][y + 1] == DIRTY;
            case WEST:
                return board[x][y - 1] == DIRTY;
            case SOUTH:
                return board[x + 1][y] == DIRTY;
            case NORTH:
                return board[x - 1][y] == DIRTY;
        }
        return false;
    }

    public static void rotate() {
        switch (dir) {
            case EAST:
                dir = NORTH;
                return;
            case WEST:
                dir = SOUTH;
                return;
            case SOUTH:
                dir = EAST;
                return;
            case NORTH:
                dir = WEST;
                return;
        }
    }

    public static void goBack() {
        switch (dir) {
            case EAST:
                y--;
                return;
            case WEST:
                y++;
                return;
            case SOUTH:
                x--;
                return;
            case NORTH:
                x++;
                return;
        }
    }

    public static boolean canGoBack() {
        switch (dir) {
            case EAST:
                return board[x][y - 1] != WALL;
            case WEST:
                return board[x][y + 1] != WALL;
            case SOUTH:
                return board[x - 1][y] != WALL;
            case NORTH:
                return board[x + 1][y] != WALL;
        }
        return false;
    }

    public static boolean good() {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (board[nx][ny] == DIRTY) {
                return false;
            }
        }
        return true;
    }
}