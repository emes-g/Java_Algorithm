package x1d;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 우선순위큐 BFS
// 벽을 부수는 횟수가 정해져 있지 않으므로, 3차원 배열(dist)을 만들지 않음
// 벽을 부수는 횟수를 기준으로 우선순위큐 BFS 수행

public class p1261 {
    static final int MAX = Integer.MAX_VALUE / 2;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int n, m;
    static int[][] arr, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];
        cnt = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = str.charAt(j - 1) - '0';
            }
            Arrays.fill(cnt[i], MAX);
        }
        PriorityQueue<tuple1261> pq = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        cnt[1][1] = 0;
        pq.offer(new tuple1261(1, 1, 0));
        while (!pq.isEmpty()) {
            tuple1261 curr = pq.poll();
            if (curr.x == n && curr.y == m) {
                System.out.println(curr.cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }
                if (cnt[nx][ny] <= cnt[curr.x][curr.y] + arr[nx][ny]) {
                    continue;
                }
                cnt[nx][ny] = cnt[curr.x][curr.y] + arr[nx][ny];
                pq.offer(new tuple1261(nx, ny, cnt[nx][ny]));
            }
        }
    }
}

class tuple1261 {
    int x, y, cnt;

    public tuple1261(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
