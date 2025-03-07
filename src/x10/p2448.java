package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2448 {
    static boolean[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new boolean[n][2 * n - 1];
        func(0, 0, n);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(board[i][j] ? '*' : ' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void func(int x, int y, int n) {
        if (n == 3) {
            board[x][y + 2] = true;
            board[x + 1][y + 1] = true;
            board[x + 1][y + 3] = true;
            for (int i = 0; i < 5; i++) {
                board[x + 2][y + i] = true;
            }
            return;
        }
        int half = n / 2;
        func(x, y + half, half);
        func(x + half, y, half);
        func(x + half, y + n, half);
    }
}
