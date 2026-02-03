package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
최대한 단순하게 시뮬레이션하는 것을 목표로 설정
- 어디에 위치할 것인지를 나타내는 curr 대신,
  기준점에서 얼만큼 이동할 것인지를 나타내는 반복변수 i를 사용함
 */

public class p14890_sol2 {
    static int n, l, ans;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 가로 줄 판정
        for (int i = 0; i < n; i++) {
            if (good(board[i])) {
                ans++;
            }
        }
        rotate();
        // 세로 줄 판정
        for (int i = 0; i < n; i++) {
            if (good(board[i])) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void rotate() {
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][(n - 1) - i] = board[i][j];
            }
        }
        board = rotated;
    }

    public static boolean good(int[] line) {
        boolean[] used = new boolean[n];
        int start = 1;
        while (start < n) {
            int diff = line[start] - line[start - 1];
            if (Math.abs(diff) > 1) {
                return false;
            }
            if (diff == 0) {
                start++;
                continue;
            }
            // DOWN
            if (diff == -1) {
                // 길이가 l인 경사로를 제작할 수 있는지
                for (int i = 0; i < l; i++) {
                    if (start + i >= n || line[start] != line[start + i]) { // 길이가 짧거나, 높이가 다른 경우
                        return false;
                    }
                    if (used[start + i]) {  // 이미 사용한 경우
                        return false;
                    }
                    used[start + i] = true;
                }
                start += l;
                continue;
            }
            // UP
            start--;    // DOWN과 달리 (start - 1)이 기준점이 되기 때문
            for (int i = 0; i < l; i++) {
                if (start - i < 0 || line[start] != line[start - i]) { // 길이가 짧거나, 높이가 다른 경우
                    return false;
                }
                if (used[start - i]) {  // 이미 사용한 경우
                    return false;
                }
                used[start - i] = true;
            }
            start += 2;
        }
        return true;
    }
}
