package x0b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1992 {
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j) - '0';
            }
        }
        func(0, 0, n);
        System.out.println(sb);
    }

    public static void func(int x, int y, int n) {
        if (check(x, y, n)) {
            sb.append(board[x][y]);
            return;
        }
        sb.append('(');
        int half = n / 2;
        func(x, y, half);
        func(x, y + half, half);
        func(x + half, y, half);
        func(x + half, y + half, half);
        sb.append(')');
    }

    public static boolean check(int x, int y, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[x][y] != board[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
