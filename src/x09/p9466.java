package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p9466 {
    static final int NOT_VISITED = 0;
    static final int CYCLE_IN = -1;

    static int n, ans;
    static int[] board, state;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            board = new int[n + 1];
            state = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                board[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= n; i++) {
                if (state[i] == NOT_VISITED) {
                    run(i);
                }
            }
            ans = n;
            for (int i = 1; i <= n; i++) {
                if (state[i] == CYCLE_IN) {
                    ans--;
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static void run(int start) {
        int curr = start;
        while (true) {
            state[curr] = start;
            curr = board[curr];
            if (state[curr] == start) { // 이번 실행에서 방문했던 노드를 재방문하는 경우
                while (state[curr] != CYCLE_IN) {
                    state[curr] = CYCLE_IN;
                    curr = board[curr];
                }
                return;
            }
            if (state[curr] != NOT_VISITED) {   // 이전 실행에서 방문했던 노드를 재방문하는 경우
                return;
            }
        }
    }
}
