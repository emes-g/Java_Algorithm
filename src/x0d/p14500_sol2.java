package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
사용자 정의 객체 기반 시뮬레이션
- 배열 회전/대칭
- 전체 경우의 수는 40개(8 * 5)인데, 중복을 제거해서 19개만 수행
- 가변 객체를 그대로 저장하면, 이후의 상태 변경에 영향을 받으므로 스냅샷(그 시점의 상태를 갖는 새로운 객체) 저장
- 배열의 깊은 비교를 할 때는 Arrays.deepXXX를 사용 (deepEquals, deepHashCode)

중복을 제거하는 버전을 연습해보려고 이렇게 풀어봤는데, 실전에서는 최초 풀이처럼 푸는게 훨씬 나을 것으로 추정
그리고 static class를 썼는데,
- 필드에 private을 붙이지 않는 이상 느려지지 않는다고 하지만,
- 제출할 때마다 동일한 코드임에도 살짝씩 느려지는 것을 확인 (그냥 원래하던 대로 외부클래스 써서 해)
 */

public class p14500_sol2 {
    static int n, m, ans = -1;
    static int[][] board;
    static Tetromino[] basics = new Tetromino[5];
    static HashSet<Tetromino> tetrominos = new HashSet<>();

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
        basics[0] = new Tetromino(new int[][]{
                {1, 1},
                {1, 1}
        });
        basics[1] = new Tetromino(new int[][]{
                {1, 1, 1, 1}
        });
        basics[2] = new Tetromino(new int[][]{
                {1, 1, 1},
                {1, 0, 0}
        });
        basics[3] = new Tetromino(new int[][]{
                {1, 1, 1},
                {0, 1, 0}
        });
        basics[4] = new Tetromino(new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        });

        for (Tetromino t : basics) {
            // 회전
            tetrominos.add(new Tetromino(t.shape)); // t를 그대로 넣으면 이후 회전/대칭에 영향받게 되므로, 스냅샷을 저장
            for (int i = 0; i < 3; i++) {
                t.rotate();
                tetrominos.add(new Tetromino(t.shape));
            }
            // 대칭
            t.turn();
            tetrominos.add(new Tetromino(t.shape));
            for (int i = 0; i < 3; i++) {
                t.rotate();
                tetrominos.add(new Tetromino(t.shape));
            }
        }
        // 테트로미노 시작
        for (Tetromino t : tetrominos) {
            run(t);
        }
        System.out.println(ans);
    }

    public static void run(Tetromino t) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int score = getScore(i, j, t);
                if (score == -1) {
                    continue;
                }
                ans = Math.max(ans, score);
            }
        }
    }

    public static int getScore(int x, int y, Tetromino t) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Tetromino)) {
                return false;
            }
            Tetromino t = (Tetromino) o;
            return Arrays.deepEquals(this.shape, t.shape);
//        if (this.sero != t.sero || this.garo != t.garo) {
//            return false;
//        }
//        for (int i = 0; i < sero; i++) {
//            for (int j = 0; j < garo; j++) {
//                if (this.shape[i][j] != t.shape[i][j]) {
//                    return false;
//                }
//            }
//        }
//        return true;
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(this.shape);
        }
    }
}