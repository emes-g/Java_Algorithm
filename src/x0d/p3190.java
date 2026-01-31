package x0d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
이벤트 기반 시뮬레이션 문제
- 큐를 2개 사용 (이벤트큐 1개, 덱 1개)
- 덱을 고른 이유는 앞에 넣고(offerFirst), 뒤에서 뺄 수 있어야 하기 때문(pollLast)
- 배열 회전이 아니라 방향 회전일 때는 dx, dy, dir 가지고 한 줄에 가능 (시계 방향, 반시계 방향)

시간복잡도는 애초에 아무리 오래 걸려도 10,000초 이내에 끝나니까 크게 고려 X
- 굳이 고려하면 사과 안 먹고 계속 뺑뺑이 도는 경우가 있으니까, O(NL)일텐데 10,000 이하임
 */

public class p3190 {
    static final int[] dx = {1, 0, -1, 0};  // 남, 동, 북, 서
    static final int[] dy = {0, 1, 0, -1};
    static final int APPLE = -1, BODY = -2;
    static final char LEFT = 'L', RIGHT = 'D';

    static int n, dir = 1;  // 현재 뱀이 바라보는 방향 (동쪽)
    static int[][] board;
    static Deque<pair3190> snake = new ArrayDeque<>();  // 뱀 자취

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        int appleNum = Integer.parseInt(br.readLine());
        while (appleNum-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            board[x][y] = APPLE;
        }
        Queue<Event3190> eventQ = new LinkedList<>();
        int eventNum = Integer.parseInt(br.readLine());
        while (eventNum-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken()) + 1;
            char dir = st.nextToken().charAt(0);
            eventQ.offer(new Event3190(time, dir));
        }
        eventQ.offer(new Event3190(Integer.MAX_VALUE / 2, LEFT));    // 더미
        snake.offer(new pair3190(0, 0));
        int time = 1;
        while (true) {
            if (time == eventQ.peek().time) {
                rotate(eventQ.poll().turn);
            }
            if (!move()) {
                System.out.println(time);
                return;
            }
            time++;
        }
    }

    public static boolean move() {
        pair3190 head = snake.peek();
        int nx = head.x + dx[dir];
        int ny = head.y + dy[dir];
        if (nx < 0 || nx >= n || ny < 0 || ny >= n) {   // 벽
            return false;
        }
        if (board[nx][ny] == BODY) {    // 몸
            return false;
        }
        snake.offerFirst(new pair3190(nx, ny)); // 머리 이동
        if (board[nx][ny] != APPLE) {
            pair3190 tail = snake.pollLast();
            board[tail.x][tail.y] = 0;  // 꼬리 자르기
        }
        board[nx][ny] = BODY;
        return true;
    }

    public static void rotate(char turn) {
        if (turn == LEFT) {
            dir = (dir + 1) % 4;    // 반시계 방향
            return;
        }
        dir = (dir + 3) % 4;    // 시계방향
    }
}

class Event3190 {
    int time;
    char turn;

    public Event3190(int time, char turn) {
        this.time = time;
        this.turn = turn;
    }
}

class pair3190 {
    int x, y;

    public pair3190(int x, int y) {
        this.x = x;
        this.y = y;
    }
}