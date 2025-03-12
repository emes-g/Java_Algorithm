package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p9663_sol1 {
    static int n, cnt;
    static int[] pos;   // 해당 깊이에서 퀸의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pos = new int[n];
        func(0);
        System.out.println(cnt);
    }

    public static void func(int depth) {
        if (depth == n) {
            cnt++;
            return;
        }
        for (int i = 0; i < n; i++) {
            pos[depth] = i;
            if (can(depth)) {
                func(depth + 1);
            }
        }
    }

    // 현 좌표에 놓은 퀸의 유효성을 검증
    public static boolean can(int depth) {
        for (int i = 0; i < depth; i++) {
            if (pos[depth] == pos[i]) { // 두 퀸이 일직선에 위치
                return false;
            }
            if (depth - i == Math.abs(pos[depth] - pos[i])) {   // 대각선에 위치
                return false;
            }
        }
        return true;
    }
}
