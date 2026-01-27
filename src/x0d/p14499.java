package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
주사위를 배열로 두고 풀어도 되는데, 현장에서 안 헷갈리게 풀고 싶음
-> 직관적 풀이: 주사위의 각 면을 변수로 두기
 */

public class p14499 {
    static final int EAST = 1, WEST = 2, SOUTH = 4, NORTH = 3;

    static int n, m, x, y;
    static int[][] board;
    static int front, rear, up, down, left, right;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (k-- > 0) {
            int dir = Integer.parseInt(st.nextToken());
            if (!canMove(dir)) {
                continue;
            }
            move(dir);
            update();
            sb.append(up).append('\n');
        }
        System.out.println(sb);
    }

    public static void update() {
        if (board[x][y] == 0) {
            board[x][y] = down;
        } else {
            down = board[x][y];
            board[x][y] = 0;
        }
    }

    public static void move(int dir) {
        int temp = up;
        switch (dir) {
            case EAST:
                y++;
                up = left;
                left = down;
                down = right;
                right = temp;
                return;
            case WEST:
                y--;
                up = right;
                right = down;
                down = left;
                left = temp;
                return;
            case SOUTH:
                x++;
                up = rear;
                rear = down;
                down = front;
                front = temp;
                return;
            case NORTH:
                x--;
                up = front;
                front = down;
                down = rear;
                rear = temp;
                return;
        }
    }

    public static boolean canMove(int dir) {
        switch (dir) {
            case EAST:
                return y < m - 1;
            case WEST:
                return y > 0;
            case SOUTH:
                return x < n - 1;
            case NORTH:
                return x > 0;
            default:
                return false;
        }
    }
}
