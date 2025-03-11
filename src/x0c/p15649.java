package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p15649 {
    static int n, m;
    static boolean[] check;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new boolean[n + 1];
        arr = new int[m];
        func(0);
        System.out.println(sb);
    }

    public static void func(int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                arr[depth] = i;
                check[i] = true;
                func(depth + 1);
                check[i] = false;
            }
        }
    }
}
