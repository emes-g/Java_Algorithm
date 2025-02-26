package x09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p9466 {
    static int n, ans;
    static int[] board;
    static boolean[] inCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            ans = n;
            board = new int[n + 1];
            inCycle = new boolean[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                board[i] = Integer.parseInt(st.nextToken());
                if (board[i] == i) {
                    ans--;
                    inCycle[i] = true;
                }
            }
            for (int i = 1; i <= n; i++) {
                if (!inCycle[i]) {
                    bfs(i);
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (board[curr] == start) { // 사이클 완성
                int len = 1;
                inCycle[start] = true;
                curr = start;
                while (board[curr] != start) {
                    len++;
                    inCycle[board[curr]] = true;
                    curr = board[curr];
                }
                ans -= len;
                return;
            }
            if (!inCycle[board[curr]]) {
                q.offer(board[curr]);
            }
        }
    }
}
