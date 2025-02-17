package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p5427 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int h, w;
    static char[][] board;
    static int[][] s, f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            board = new char[h][w];
            s = new int[h][w];
            f = new int[h][w];
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    board[i][j] = str.charAt(j);
                    s[i][j] = f[i][j] = -1;
                }
            }

            // 불에 대한 BFS 선행
            Queue<pair5427> q = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == '*') {
                        f[i][j] = 0;
                        q.offer(new pair5427(i, j));
                    }
                }
            }
            while (!q.isEmpty()) {
                pair5427 curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        continue;
                    }
                    if (board[nx][ny] == '#' || f[nx][ny] != -1) {
                        continue;
                    }
                    f[nx][ny] = f[curr.x][curr.y] + 1;
                    q.offer(new pair5427(nx, ny));
                }
            }

            // 상근이에 대한 BFS
            q = new LinkedList<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (board[i][j] == '@') {
                        s[i][j] = 0;
                        q.offer(new pair5427(i, j));
                        i = h;
                        break;
                    }
                }
            }
            boolean success = false;
            while (!q.isEmpty()) {
                pair5427 curr = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = curr.x + dx[i];
                    int ny = curr.y + dy[i];
                    if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                        success = true;
                        sb.append(s[curr.x][curr.y] + 1).append('\n');
                        q.clear();
                        break;
                    }
                    if (board[nx][ny] == '#' || s[nx][ny] != -1) {
                        continue;
                    }
                    if (f[nx][ny] != -1 && (f[nx][ny] <= s[curr.x][curr.y] + 1)) {
                        continue;
                    }
                    s[nx][ny] = s[curr.x][curr.y] + 1;
                    q.offer(new pair5427(nx, ny));
                }
            }
            if (!success) {
                sb.append("IMPOSSIBLE\n");
            }
        }
        System.out.println(sb);
    }
}

class pair5427 {
    int x, y;

    public pair5427(int x, int y) {
        this.x = x;
        this.y = y;
    }
}