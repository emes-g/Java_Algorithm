package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p1941 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static char[][] board = new char[5][5];
    static boolean[] check = new boolean[25];
    static int[] arr = new int[7];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        func(0, 0);
        System.out.println(ans);
    }

    public static void func(int depth, int start) {
        if (depth == 7) {   // 7명을 선택한 경우
            if (bfs()) {
                ans++;
            }
            return;
        }
        if (start >= 25) {
            return;
        }
        check[start] = true;
        arr[depth] = start;
        func(depth + 1, start + 1);
        check[start] = false;
        func(depth, start + 1);
    }

    public static boolean bfs() {
        boolean[] visited = new boolean[25];
        Queue<Integer> q = new LinkedList<>();
        q.offer(arr[0]);
        visited[arr[0]] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int curr = q.poll();
            int x = curr / 5;
            int y = curr % 5;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int next = nx * 5 + ny;
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }
                if (!check[next] || visited[next]) {    // 칠공주가 아니거나, 방문한 적이 있는 칠공주라면
                    continue;
                }
                visited[next] = true;
                q.offer(next);
                cnt++;
            }
        }
        if (cnt != 7) {
            return false;
        }
        int s = 0;
        for (int i = 0; i < 7; i++) {
            int x = arr[i] / 5;
            int y = arr[i] % 5;
            if (board[x][y] == 'S') {
                s++;
            }
        }
        return s >= 4;
    }
}