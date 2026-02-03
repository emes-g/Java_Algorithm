package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
분기 처리 메인 시뮬레이션 문제

임시변수 curr을 두고 진행하게 되면서, 분기가 훨씬 복잡해졌음 (sol2랑 비교하면 직관성 차이가 명확함)
- 그래서 sol2에서는 임시변수 curr을 사용하지 않고, 반복변수 i로만 대체함
- 또한 반복문에서 '높이가 달라지는지'뿐만 아니라 '이미 경사로에 사용한 칸인지'도 판별할 수 있는데, 이를 끝내고 했음
  - 사실 한 번에 해주는게 좋지만, 시험장에서 헷갈릴 거 같으면 지금처럼 해도 되긴 함
 */

public class p14890 {
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
            if (Math.abs(diff) > 1) {  // 인접한 칸의 높이 차가 1을 초과하는 경우
                return false;
            }
            if (diff == 0) {   // SAME
                start++;
                continue;
            }
            // DOWN: 현재 위치부터 l칸 갈 수 있는지
            if (diff == -1) {
                int curr = start + 1;
                while (curr < n) {
                    if (curr - start >= l) {    // [start, curr) 길이가 l이 된 경우
                        break;
                    }
                    if (line[start] != line[curr]) {    // (l칸이 되기 전에) 높이가 달라진 경우
                        return false;
                    }
                    curr++;
                }
                if (curr - start < l) { // 칸의 길이가 모자란 경우
                    return false;
                }
                for (int i = start; i < curr; i++) {
                    if (used[i]) {    // 이미 경사로 제작에 사용했던 칸인 경우
                        return false;
                    }
                    used[i] = true;
                }
                start = curr;
                continue;
            }
            // UP: 이전 위치부터 l칸 갈 수 있는지
            start--;    // 최대한 DOWN 코드를 활용하기 위해서
            int curr = start;
            while (curr >= 0) {
                if (start - curr >= l) {    // [start, curr) 길이가 l이 된 경우
                    break;
                }
                if (line[start] != line[curr]) {    // (l칸이 되기 전에) 높이가 달라진 경우
                    return false;
                }
                curr--;
            }
            if (start - curr < l) { // 칸의 길이가 모자란 경우
                return false;
            }
            for (int i = start; i > curr; i--) {
                if (used[i]) {    // 이미 경사로 제작에 사용했던 칸인 경우
                    return false;
                }
                used[i] = true;
            }
            start += 2;
        }
        return true;
    }
}
