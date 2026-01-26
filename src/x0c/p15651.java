package x0c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중복 순열 (nπm)

public class p15651 {
    static int n, m;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        func(0);
        System.out.println(sb);
    }

    public static void func(int level) {
        if (level == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= n; i++) {
            arr[level] = i;
            func(level + 1);
        }
    }
}
