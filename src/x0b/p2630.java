package x0b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2630 {
    static int[][] board;
    static int[] cnt = new int[2];

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
            cnt[board[x][y]]++;
            return;
        }
        int half = n / 2;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                func(x + i * half, y + j * half, n / 2);
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
