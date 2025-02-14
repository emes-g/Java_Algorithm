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
    static char[][] board;
    static int[][] jDist, fDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r + 1][c + 1];
        jDist = new int[r + 1][c + 1];
        fDist = new int[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            String str = br.readLine();
            for (int j = 1; j <= c; j++) {
                board[i][j] = str.charAt(j - 1);
                jDist[i][j] = fDist[i][j] = -1;
            }
        }

        // 불에 대한 BFS 먼저
        Queue<pair4179> q = new LinkedList<>();
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (board[i][j] == 'F') {
                    fDist[i][j] = 0;
                    q.offer(new pair4179(i, j));
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
                if (board[nx][ny] == '#' || fDist[nx][ny] != -1) {
                    continue;
                }
                fDist[nx][ny] = fDist[curr.x][curr.y] + 1;
                q.offer(new pair4179(nx, ny));
            }
        }

        // 이후, 지훈이에 대한 BFS
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (board[i][j] == 'J') {
                    jDist[i][j] = 0;
                    q.offer(new pair4179(i, j));
                    i = r;
                    break;
                }
            }
        }
        while (!q.isEmpty()) {
            pair4179 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > r || ny < 1 || ny > c) { // 지훈이가 맵 밖으로 탈출한 경우
                    System.out.println(jDist[curr.x][curr.y] + 1);
                    return;
                }
                if (board[nx][ny] == '#' || jDist[nx][ny] != -1) {
                    continue;
                }
                if (fDist[nx][ny] != -1 && (fDist[nx][ny] <= jDist[curr.x][curr.y] + 1)) {  // 지훈이가 가기에 앞서, 불이 난 공간인 경우
                    continue;
                }
                jDist[nx][ny] = jDist[curr.x][curr.y] + 1;
                q.offer(new pair4179(nx, ny));
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}

class pair4179 {
    int x, y;

    public pair4179(int x, int y) {
        this.x = x;
        this.y = y;
    }
}