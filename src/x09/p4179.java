package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p4179 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int r, c;
    static char[][] j, f;
    static int[][] jDist, fDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        j = new char[r + 1][c + 1];
        f = new char[r + 1][c + 1];
        jDist = new int[r + 1][c + 1];
        fDist = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            String str = br.readLine();
            for (int k = 1; k <= c; k++) {
                char c = str.charAt(k - 1);
                j[i][k] = f[i][k] = c;
                jDist[i][k] = fDist[i][k] = -1;
                if (c == 'J') {
                    f[i][k] = '.';
                } else if (c == 'F') {
                    j[i][k] = '#';
                }
            }
        }
        bfs(j, jDist);
        bfs(f, fDist);

        int min = Integer.MAX_VALUE;
        // 위, 아래
        for (int i = 1; i <= r; i += (r - 1)) {
            for (int k = 1; k <= c; k++) {
                if (j[i][k] == '#' || jDist[i][k] == -1) {
                    continue;
                }
                if (fDist[i][k] == -1 || jDist[i][k] < fDist[i][k]) {
                    min = Math.min(min, jDist[i][k]);
                }
            }
        }
        // 왼쪽, 오른쪽
        for (int i = 2; i <= r - 1; i++) {
            for (int k = 1; k <= c; k += (c - 1)) {
                if (j[i][k] == '#' || jDist[i][k] == -1) {
                    continue;
                }
                if (fDist[i][k] == -1 || (jDist[i][k] < fDist[i][k])) {
                    min = Math.min(min, jDist[i][k]);
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            return;
        }
        System.out.println(min + 1);
    }

    public static void bfs(char[][] board, int[][] dist) {
        Queue<pair4179> q = new LinkedList<>();
        for (int i = 1; i <= r; i++) {
            for (int k = 1; k <= c; k++) {
                if (board[i][k] == 'J' || board[i][k] == 'F') {
                    dist[i][k] = 0;
                    q.offer(new pair4179(i, k));
                }
            }
        }
        while (!q.isEmpty()) {
            pair4179 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > r || ny < 1 || ny > c) {
                    continue;
                }
                if (board[nx][ny] == '#' || dist[nx][ny] != -1) {
                    continue;
                }
                dist[nx][ny] = dist[curr.x][curr.y] + 1;
                q.offer(new pair4179(nx, ny));
            }
        }
    }
}

class pair4179 {
    int x, y;

    public pair4179(int x, int y) {
        this.x = x;
        this.y = y;
    }
}