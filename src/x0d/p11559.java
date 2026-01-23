package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class p11559 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static final int N = 12, M = 6;

    static char[][] board = new char[N][M];
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        while (true) {
            // 1. 크기가 4 이상인 연결 요소 터뜨리기
            visited = new boolean[N][M];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    if (visited[i][j]) {
                        continue;
                    }
                    if (!bfs(i, j)) {
                        continue;
                    }
                    cnt++;  // 연결 요소를 터뜨린 경우
                }
            }
            if (cnt == 0) {
                break;
            }
            // 2. 보드 갱신하기 (중력 반영)
            for (int j = 0; j < M; j++) {
                Stack<Character> stack = new Stack<>();
                for (int i = 0; i < N; i++) {
                    if (board[i][j] == '.') {
                        continue;
                    }
                    stack.push(board[i][j]);
                    board[i][j] = '.';
                }
                int idx = N - 1;
                while (!stack.isEmpty()) {
                    board[idx][j] = stack.pop();
                    idx--;
                }
            }
            // 3. 연쇄 판정
            ans++;
        }
        System.out.println(ans);
    }

    public static boolean bfs(int x, int y) {
        Queue<pair11559> q = new LinkedList<>();
        Stack<pair11559> stack = new Stack<>(); // 경로 복원용
        pair11559 start = new pair11559(x, y);
        // 시작점 처리
        q.offer(start);
        visited[x][y] = true;
        stack.push(start);
        while (!q.isEmpty()) {
            pair11559 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (visited[nx][ny] || board[nx][ny] != board[x][y]) {
                    continue;
                }
                pair11559 next = new pair11559(nx, ny);
                // 넣는 시점에 방문 표시
                q.offer(next);
                visited[nx][ny] = true;
                stack.push(next);
            }
        }
        if (stack.size() < 4) {
            return false;
        }
        while (!stack.isEmpty()) {
            pair11559 curr = stack.pop();
            board[curr.x][curr.y] = '.';
        }
        return true;
    }

    public static void showBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.printf("%c ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}

class pair11559 {
    int x, y;

    public pair11559(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
