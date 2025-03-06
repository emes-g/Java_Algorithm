package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p2447 {
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], true);
        }
        func(0, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j] ? '*' : ' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void func(int x, int y, int n) {
        if (n == 3) {
            board[x + 1][y + 1] = false;
            return;
        }
        int step = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    fillUpWithBlank(x + i * step, y + j * step, step);
                    continue;
                }
                func(x + i * step, y + j * step, n / 3);
            }
        }
    }

    public static void fillUpWithBlank(int x, int y, int step) {
        for (int i = 0; i < step; i++) {
            for (int j = 0; j < step; j++) {
                board[x + i][y + j] = false;
            }
        }
    }
}
