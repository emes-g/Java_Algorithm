package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
알고리즘:
1. 벽 3개 만들기 (64C3)
2. 바이러스 확산
3. 안전 영역 갱신

최적화
1. 벽 3개 만들 때, 보드를 전부 돌지 않고 빈 칸만 돌도록 emptyList 추가
2. 바이러스 확산할 때, 처음에 모든 바이러스를 큐에 넣어두고 시작 (바이러스 위치는 virusList로 추적)
-> 빅오 표기법 상으로는 시간복잡도가 같지만, '이런 관점으로 생각할 수도 있구나' 정도의 insight

그리고..
- 참조 타입(사용자 정의 클래스, Wrapper 클래스 등)으로 객체를 생성하는 것이 시간/메모리 측면에서 비효율적이었던 원인임
  - '객체가 가변이냐 불변이냐'가 문제가 아니고 애초에 참조 타입이기 때문에 heap 메모리에 계속 누적되어 GC가 발생하는게 문제
  - 당장 문제 해결에는 크게 지장은 없는데, 내부적으로 이렇게 동작하기 때문에 '시간/메모리 측면이 비효율적이었구나' 알아두기
 */

public class p14502_sol2 {
    static final int EMPTY = 0, WALL = 1, VIRUS = 2;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int n, m, ans;
    static int[][] board;
    static boolean[][] visited;
    static ArrayList<Pair> emptyList = new ArrayList<>();
    static ArrayList<Pair> virusList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == EMPTY) {
                    emptyList.add(new Pair(i, j));
                } else if (board[i][j] == VIRUS) {
                    virusList.add(new Pair(i, j));
                }
            }
        }
        func(0, 0);
        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        // 바이러스들을 한번에 큐에 넣어놓고 시작 (넣는 시점에 방문 표시)
        for (Pair p : virusList) {
            visited[p.x][p.y] = true;
            q.offer(new Pair(p.x, p.y));
        }
        while (!q.isEmpty()) {
            Pair curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (board[nx][ny] == WALL || visited[nx][ny]) {
                    continue;
                }
                // 넣는 시점에 방문 표시
                visited[nx][ny] = true;
                q.offer(new Pair(nx, ny));
            }
        }
    }

    private static void func(int level, int start) {
        if (level == 3) {
            // 2. 바이러스 확산
            for (int i = 0; i < n; i++) {
                Arrays.fill(visited[i], false);
            }
            bfs();

            // 3. 안전 영역 갱신
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == EMPTY && !visited[i][j]) {
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        if (start >= emptyList.size()) {
            return;
        }
        // 1. 벽 3개 만들기 (보드를 전부 돌지 않고, 빈 칸만 돌기)
        for (int idx = start; idx < emptyList.size(); idx++) {
            Pair empty = emptyList.get(idx);
            board[empty.x][empty.y] = WALL;
            func(level + 1, idx + 1);
            board[empty.x][empty.y] = EMPTY;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}