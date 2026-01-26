package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
시간복잡도: O(NK)

[프로세스]
1. 회전시킬 톱니바퀴와 방향 결정
2. 회전에 앞서, 양옆 맞닿은 극 확인
- 반대극이면 인접한 톱니바퀴를 반대 방향으로 회전하기로 결정 (실제 회전은 아직 X)
- 해당 톱니바퀴의 양옆 맞닿은 극 확인
- 위 과정을 반복해, 어느 톱니바퀴까지 회전할지 결정했다면 순차적으로(순서는 상관X) 회전
 */

public class p14891 {
    static final int N = 4, M = 8;
    static final int CLOCKWISE = 1, REVERSE = -1;

    static int[][] board = new int[N + 1][M];   // 1-index
    static int[] cursor = new int[N + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if (str.charAt(j) == '1') {
                    board[i][j] = 1;
                }
            }
        }
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 1~4
            int dir = Integer.parseInt(st.nextToken()); // CLOCKWISE or REVERSE
            cascadeLeft(num, dir);
            cascadeRight(num, dir);
            rotate(num, dir);
        }
        // 점수 계산
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += board[i][cursor[i]] * (1 << (i - 1));
        }
        System.out.println(ans);
    }

    public static void cascadeLeft(int num, int dir) {
        if (num == 1) {
            return;
        }
        int curr = (cursor[num] + 6) % M;
        int left = (cursor[num - 1] + 2) % M;
        if (board[num][curr] == board[num - 1][left]) {
            return;
        }
        int reverseDir = (dir == CLOCKWISE) ? REVERSE : CLOCKWISE;
        cascadeLeft(num - 1, reverseDir);
        rotate(num - 1, reverseDir);
    }

    public static void cascadeRight(int num, int dir) {
        if (num == N) {
            return;
        }
        int curr = (cursor[num] + 2) % M;
        int right = (cursor[num + 1] + 6) % M;
        if (board[num][curr] == board[num + 1][right]) {
            return;
        }
        int reverseDir = (dir == CLOCKWISE) ? REVERSE : CLOCKWISE;
        cascadeRight(num + 1, reverseDir);
        rotate(num + 1, reverseDir);
    }

    public static void rotate(int num, int dir) {
        if (dir == CLOCKWISE) {
            cursor[num] = (cursor[num] - 1 + M) % M;
        } else {
            cursor[num] = (cursor[num] + 1) % M;
        }
    }
}
