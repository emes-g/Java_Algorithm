package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
사용자 정의 객체 기반 시뮬레이션 (최초 풀이)
- 배열 회전/대칭
 */

public class p14500 {
    static int n, m, ans = -1;
    static int[][] board;
    static Tetromino[] tetrominos = new Tetromino[5];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tetrominos[0] = new Tetromino(new int[][]{
                {1, 1},
                {1, 1}
        });
        tetrominos[1] = new Tetromino(new int[][]{
                {1, 1, 1, 1}
        });
        tetrominos[2] = new Tetromino(new int[][]{
                {1, 1, 1},
                {1, 0, 0}
        });
        tetrominos[3] = new Tetromino(new int[][]{
                {1, 1, 1},
                {0, 1, 0}
        });
        tetrominos[4] = new Tetromino(new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        });
        for (int num = 0; num < 5; num++) {
            // 회전
            run(num);
            for (int i = 0; i < 3; i++) {
                tetrominos[num].rotate();
                run(num);
            }
            // 대칭
            tetrominos[num].turn();
            run(num);
            for (int i = 0; i < 3; i++) {
                tetrominos[num].rotate();
                run(num);
            }
        }
        System.out.println(ans);
    }

    private static void run(int num) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int score = getScore(i, j, num);
                if (score == -1) {
                    continue;
                }
                ans = Math.max(ans, score);
            }
        }
    }

    private static int getScore(int x, int y, int num) {
        Tetromino t = tetrominos[num];
        if (x < 0 || x + t.sero > n || y < 0 || y + t.garo > m) {
            return -1;
        }
        int score = 0;
        for (int i = 0; i < t.sero; i++) {
            for (int j = 0; j < t.garo; j++) {
                score += (board[x + i][y + j] * t.shape[i][j]);
            }
        }
        return score;
    }

    static class Tetromino {
        int[][] shape;
        int sero, garo;

        public Tetromino(int[][] shape) {
            this.shape = shape;
            this.sero = shape.length;
            this.garo = shape[0].length;
        }

        public void rotate() {
            int[][] rotated = new int[garo][sero];
            for (int i = 0; i < sero; i++) {
                for (int j = 0; j < garo; j++) {
                    rotated[j][(sero - 1) - i] = shape[i][j];
                }
            }
            shape = rotated;
            sero = rotated.length;
            garo = rotated[0].length;
        }

        // 상하만 대칭을 해도 360도 회전을 하기 때문에 모든 경우의 수가 고려됨
        public void turn() {
            int[][] turned = new int[sero][garo];
            for (int i = 0; i < sero; i++) {
                for (int j = 0; j < garo; j++) {
                    turned[(sero - 1) - i][j] = shape[i][j];
                }
            }
            shape = turned;
        }
    }
}