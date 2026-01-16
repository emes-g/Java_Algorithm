package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
감시랑 비슷한 알고리즘으로 해결
1. 한 라인 가져오기
2. 한 라인 내에서 합치기 (trim X)
3. 보드 갱신하기 (trim 수행까지)
 */

public class p12100 {
    static int n, ans;
    static int[][] board;
    static int[] line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(0);
        System.out.println(ans);
    }

    public static void func(int level) {
        if (level == 5) {
            // 현재 상태에서 가장 큰 블록 색출
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ans = Math.max(ans, board[i][j]);
                }
            }
            return;
        }
        // 백업용 배열 생성
        int[][] backup = new int[n][n];
        for (int i = 0; i < n; i++) {
            backup[i] = Arrays.copyOf(board[i], n);
        }
        for (int dir = 0; dir < 4; dir++) {   // 상하좌우
            move(dir); // 지정된 방향으로 이동
            func(level + 1);
            // 백업
            for (int i = 0; i < n; i++) {
                board[i] = Arrays.copyOf(backup[i], n);
            }
        }
    }

    private static void move(int dir) {
        line = new int[n];
        switch (dir) {
            case 0: // 상
                for (int j = 0; j < n; j++) {   // 모든 열에 대해서
                    // 라인 가져오기
                    for (int i = 0; i < n; i++) {
                        line[i] = board[i][j];
                    }
                    // 합치기
                    combineElements();
                    // 보드 갱신
                    int idx = 0;
                    for (int i = 0; i < n; i++) {
                        if (line[i] == 0) {
                            continue;
                        }
                        board[idx++][j] = line[i];
                    }
                    for (int i = idx; i < n; i++) {
                        board[i][j] = 0;
                    }
                }
                break;
            case 1: // 하
                for (int j = 0; j < n; j++) {   // 모든 열에 대해서
                    // 라인 가져오기
                    for (int i = 0; i < n; i++) {
                        line[i] = board[(n - 1) - i][j];
                    }
                    // 합치기
                    combineElements();
                    // 보드 갱신
                    int idx = n - 1;
                    for (int i = 0; i < n; i++) {
                        if (line[i] == 0) {
                            continue;
                        }
                        board[idx--][j] = line[i];
                    }
                    for (int i = idx; i >= 0; i--) {
                        board[i][j] = 0;
                    }
                }
                break;
            case 2: // 좌
                for (int i = 0; i < n; i++) {   // 모든 행에 대해서
                    // 라인 가져오기
                    for (int j = 0; j < n; j++) {
                        line[j] = board[i][j];
                    }
                    // 합치기
                    combineElements();
                    // 보드 갱신
                    int idx = 0;
                    for (int j = 0; j < n; j++) {
                        if (line[j] == 0) {
                            continue;
                        }
                        board[i][idx++] = line[j];
                    }
                    for (int j = idx; j < n; j++) {
                        board[i][j] = 0;
                    }
                }
                break;
            case 3: // 우
                for (int i = 0; i < n; i++) {   // 모든 행에 대해서
                    // 라인 가져오기
                    for (int j = 0; j < n; j++) {
                        line[j] = board[i][(n - 1) - j];
                    }
                    // 합치기
                    combineElements();
                    // 보드 갱신
                    int idx = n - 1;
                    for (int j = 0; j < n; j++) {
                        if (line[j] == 0) {
                            continue;
                        }
                        board[i][idx--] = line[j];
                    }
                    for (int j = idx; j >= 0; j--) {
                        board[i][j] = 0;
                    }
                }
                break;
        }
    }

    // 한 줄에 있는 요소들의 합치기 기능만 O(n)에 수행 (trim X)
    private static void combineElements() {
        for (int i = 0; i < n - 1; i++) {
            int idx = i + 1;
            // 다음 요소 찾기
            while (idx < n && line[idx] == 0) {
                idx++;
            }
            if (idx == n) { // 못 찾은 경우
                return;
            }
            // 합칠 수 없는 경우
            if (line[i] != line[idx]) {
                i = idx - 1;
                continue;
            }
            // 합칠 수 있는 경우
            line[i] *= 2;
            line[idx] = 0;
            i = idx;
        }
    }

    public static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d\t", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
