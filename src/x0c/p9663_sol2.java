package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9663_sol2 {
    static int n, cnt;
    static boolean[] col, diag1, diag2; // 해당 라인에서 퀸의 존재 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        func(0);
        System.out.println(cnt);
    }

    public static void func(int depth) {
        if (depth == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            int dir1 = depth + i;   // 우상향
            int dir2 = depth - i + (n - 1); // 우하향
            if (col[i] || diag1[dir1] || diag2[dir2]) {
                continue;
            }
            col[i] = diag1[dir1] = diag2[dir2] = true;
            func(depth + 1);
            col[i] = diag1[dir1] = diag2[dir2] = false;
        }
    }
}
