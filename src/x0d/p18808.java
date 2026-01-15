package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 스티커 붙일 수 있는지 판단
2. 가능하면 붙이고 다음 스티커로
3. 불가능하면 스티커 90도 회전 후 1번으로
 */

public class p18808 {
    static boolean[][] board, sticker;
    static int n, m, r, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // 스티커 모양 잡기
            sticker = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    if (st.nextToken().equals("1")) {
                        sticker[i][j] = true;
                    }
                }
            }
            // 스티커 붙이기
            for (int rotate = 0; rotate < 4; rotate++) {
                // 스티커 붙이기 시도
                if (tryPlaceSticker()) {
                    break;
                }
                // 스티커 회전
                rotateSticker();
            }
        }
        // 스티커가 붙은 칸 수 세기
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void rotateSticker() {
        // 90도 회전한 스티커 생성
        boolean[][] newSticker = new boolean[c][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newSticker[j][(r - 1) - i] = sticker[i][j];
            }
        }
        // 기존 스티커 덮어쓰기
        sticker = new boolean[c][r];
        for (int i = 0; i < c; i++) {
            sticker[i] = Arrays.copyOf(newSticker[i], r);
        }
        // r, c 스왑
        int temp = r;
        r = c;
        c = temp;
    }

    public static boolean tryPlaceSticker() {
        for (int i = 0; i <= n - r; i++) {
            for (int j = 0; j <= m - c; j++) {
                if (canPlaceSticker(i, j)) {
                    placeSticker(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    private static void placeSticker(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[x + i][y + j] |= sticker[i][j];
            }
        }
    }

    private static boolean canPlaceSticker(int x, int y) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[x + i][y + j] && sticker[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
