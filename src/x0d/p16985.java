package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
3차원 BFS + 순열 + 중복순열 문제
- 단순히 3차원 BFS가 아니라, 2차원 board 5개의 순서를 자유롭게 배치할 수 있고, 각 board가 회전할 수 있음
- 매 경우의 수에서 회전을 계산하려고 하면 TLE 발생할 것 → 사전에 회전 가능한 경우의 수 구해두기
- 때문에 사전에 경우의 수를 구해둬야 함 → 5!(배치 순서) * 4^5(2차원 board별 가능한 방향)

시간 복잡도: O(5! * 4^5 * 5^3) == O(1536만)
 */

public class p16985 {
    static final int[] dz = {1, -1};
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int[][][][] board = new int[5][4][5][5]; // [판][방향][행][열]
    static int[] arr = new int[5];  // 판을 쌓는 순서
    static int[] arr2 = new int[5]; // 판의 회전 방향 (시계방향) (각 값은 0도, 90도, 180도, 270도에 대응)
    static boolean[] isUsed = new boolean[5];
    static int[][][] dist = new int[5][5][5];
    static int ans = (1 << 20);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 5; j++) {
                    board[k][0][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        // 각 판을 회전한 경우의 수를 미리 저장
        for (int k = 0; k < 5; k++) {
            for (int dir = 0; dir < 3; dir++) {
                rotate(k, dir);
            }
        }
        func(0);
        System.out.println(ans == (1 << 20) ? -1 : ans);
    }

    public static void bfs() {
        // 판 순서와 방향이 유동적이므로, 유효하지 않은 출발점과 도착점인 경우가 발생 가능하여 가지치기
        if (board[arr[0]][arr2[0]][0][0] == 0 || board[arr[4]][arr2[4]][4][4] == 0) {
            return;
        }
        Queue<tuple16985> q = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        // 시작점 처리
        dist[0][0][0] = 0;
        q.offer(new tuple16985(0, 0, 0));
        while (!q.isEmpty()) {
            tuple16985 curr = q.poll();
            // 판 내부 이동
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }
                if (dist[curr.z][nx][ny] != -1 || board[arr[curr.z]][arr2[curr.z]][nx][ny] == 0) {
                    continue;
                }
                // 넣는 시점에 방문 표시
                dist[curr.z][nx][ny] = dist[curr.z][curr.x][curr.y] + 1;
                q.offer(new tuple16985(curr.z, nx, ny));
            }
            // 판의 이동
            for (int i = 0; i < 2; i++) {
                int nz = curr.z + dz[i];
                if (nz < 0 || nz >= 5) {
                    continue;
                }
                if (dist[nz][curr.x][curr.y] != -1 || board[arr[nz]][arr2[nz]][curr.x][curr.y] == 0) {
                    continue;
                }
                // 넣는 시점에 방문 표시
                dist[nz][curr.x][curr.y] = dist[curr.z][curr.x][curr.y] + 1;
                q.offer(new tuple16985(nz, curr.x, curr.y));
            }
        }
        if (dist[4][4][4] == -1) {
            return;
        }
        ans = Math.min(ans, dist[4][4][4]);
    }

    // 중복 순열: 판의 회전 방향 결정
    public static void func2(int level) {
        if (level == 5) {
            bfs();
            return;
        }
        for (int dir = 0; dir < 4; dir++) {
            arr2[level] = dir;
            func2(level + 1);
        }
    }

    // 순열: 판을 쌓는 순서 결정
    public static void func(int level) {
        if (level == 5) {
            func2(0);
            return;
        }
        for (int k = 0; k < 5; k++) {
            if (isUsed[k]) {
                continue;
            }
            isUsed[k] = true;
            arr[level] = k;
            func(level + 1);
            isUsed[k] = false;
        }
    }

    // k번 판을 시계 방향으로 90*(dir+1)도 회전
    public static void rotate(int k, int dir) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[k][dir + 1][j][4 - i] = board[k][dir][i][j];
            }
        }
    }
}

class tuple16985 {
    int z, x, y;

    public tuple16985(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }
}