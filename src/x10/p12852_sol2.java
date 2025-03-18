package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p12852_sol2 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        bfs(1);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        arr[start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == n) {
                StringBuilder sb = new StringBuilder();
                int cnt = 0;
                int val = curr;
                while (true) {
                    sb.append(val).append(' ');
                    if (val == start) {
                        break;
                    }
                    val = arr[val];
                    cnt++;
                }
                System.out.println(cnt);
                System.out.println(sb);
                return;
            }
            for (int next : new int[]{curr + 1, curr * 2, curr * 3}) {
                if (next > n) {
                    break;
                }
                if (arr[next] == 0) {
                    arr[next] = curr;
                    q.offer(next);
                }
            }
        }
    }
}
