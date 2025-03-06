package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1780 {
    static int[][] board;
    static int[] cnt = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(0, 0, n);
        for (int val : cnt) {
            System.out.println(val);
        }
    }

    public static void func(int x, int y, int n) {
        if (check(x, y, n)) {
            cnt[board[x][y] + 1]++;
            return;
        }
        int step = n / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                func(x + i * step, y + j * step, n / 3);
            }
        }
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
