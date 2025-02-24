package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p2146 {
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n;
    static int[][] board, dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 대륙 구분
        int areaNum = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    bfs(i, j, areaNum++);
                }
            }
        }
        // 대륙 별로 최단 다리 찾기
        int ans = Integer.MAX_VALUE;
        for (int i = 2; i < areaNum; i++) {
            ans = Math.min(ans, findShortestBridge(i));
        }
        System.out.println(ans);
    }

    public static void bfs(int x, int y, int areaNum) {
        Queue<pair2146> q = new LinkedList<>();
        board[x][y] = areaNum;
        q.offer(new pair2146(x, y));
        while (!q.isEmpty()) {
            pair2146 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (board[nx][ny] != 1) {
                    continue;
                }
                board[nx][ny] = areaNum;
                q.offer(new pair2146(nx, ny));
            }
        }
    }

    public static int findShortestBridge(int areaNum) {
        Queue<pair2146> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }
        // 출발점
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == areaNum) {
                    dist[i][j] = 0;
                    q.offer(new pair2146(i, j));
                }
            }
        }
        // 최단거리 다리 찾기
        while (!q.isEmpty()) {
            pair2146 curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }
                if (board[nx][ny] != 0 && board[nx][ny] != areaNum) {   // 신대륙 발견
                    return dist[curr.x][curr.y];
                }
                if (board[nx][ny] == 0 && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[curr.x][curr.y] + 1;
                    q.offer(new pair2146(nx, ny));
                }
            }
        }
        return -1;
    }
}

class pair2146 {
    int x, y;

    public pair2146(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
