package x0d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
CCTV 종류 : 1~5번
CCTV 종류별 방향 SET
- 1번 : 4개 (동, 서, 남, 북)
- 2번 : 2개 (동서, 남북)
- 3번 : 4개 (동남, 동북, 서남, 서북)
- 4번 : 4개 (동서남, 동서북, 남북동, 남북서)
- 5번 : 1개 (동서남북)
→   해당 CCTV 종류가 가질 수 있는 모든 방향 SET에 대해 루프
    가령, 3번이라고 하면 동남/동북/서남/서북에 대해 루프

기존 풀이보다 조금 더 비효율적이긴 한데, 훨씬 직관적이어서 실전에서는 더 유용할 듯
 */

public class p15683_sol2 {
    static final String[] one = {"0", "1", "2", "3"};
    static final String[] two = {"01", "23"};
    static final String[] three = {"02", "03", "12", "13"};
    static final String[] four = {"012", "013", "230", "231"};
    static final String[] five = {"0123"};

    static int n, m, k, ans = 100;
    static int[][] state;
    static ArrayList<cctv15683_sol2> cctvList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] >= 1 && board[i][j] <= 5) {
                    cctvList.add(new cctv15683_sol2(i, j, board[i][j]));
                }
            }
        }
        k = cctvList.size();
        state = new int[n][m];
        for (int i = 0; i < n; i++) {
            state[i] = Arrays.copyOf(board[i], m);
        }
        func(0);
        System.out.println(ans);
    }

    public static void func(int level) {
        if (level == k) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (state[i][j] == 0) {
                        cnt++;
                    }
                }
            }
            ans = Math.min(ans, cnt);
            return;
        }
        // CCTV 종류에 따른 방향 SET 결정
        cctv15683_sol2 cctv = cctvList.get(level);
        String[] dirSet = getDirSet(cctv.type);
        // 상태 백업용
        int[][] backup = new int[n][m];
        for (int i = 0; i < n; i++) {
            backup[i] = Arrays.copyOf(state[i], m);
        }
        // 백트래킹
        for (String element : dirSet) {
            for (char dir : element.toCharArray()) {
                fillLine(cctv.x, cctv.y, dir);
            }
            func(level + 1);
            // 상태 백업
            for (int i = 0; i < n; i++) {
                state[i] = Arrays.copyOf(backup[i], m);
            }
        }
    }

    public static void fillLine(int x, int y, char dir) {
        switch (dir) {
            case '0':   // 동
                for (int j = y + 1; j < m; j++) {
                    if (existObstacle(x, j)) {
                        return;
                    }
                    state[x][j] = 7;
                }
                break;
            case '1':   // 서
                for (int j = y - 1; j >= 0; j--) {
                    if (existObstacle(x, j)) {
                        return;
                    }
                    state[x][j] = 7;
                }
                break;
            case '2':   // 남
                for (int i = x + 1; i < n; i++) {
                    if (existObstacle(i, y)) {
                        return;
                    }
                    state[i][y] = 7;
                }
                break;
            case '3':   // 북
                for (int i = x - 1; i >= 0; i--) {
                    if (existObstacle(i, y)) {
                        return;
                    }
                    state[i][y] = 7;
                }
                break;
        }
    }

    public static boolean existObstacle(int x, int y) {
        return state[x][y] == 6;
    }

    public static String[] getDirSet(int cctvType) {
        switch (cctvType) {
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return three;
            case 4:
                return four;
            case 5:
                return five;
        }
        return new String[]{};
    }
}

class cctv15683_sol2 {
    int x, y, type;

    public cctv15683_sol2(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
