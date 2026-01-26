package x0c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
오름차순 순열: 기본 순열(nPm)에 시작 인덱스(start)만 추가
 */

public class p15650 {
    static int n, m;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        visited = new boolean[n + 1];   // 1-index
        func(0, 1);
        System.out.println(sb);
    }

    public static void func(int level, int start) {
        if (level == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = start; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            arr[level] = i;
            func(level + 1, i + 1);
            visited[i] = false;
        }
    }
}
