package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p15664 {
    static int n, m;
    static boolean[] check;
    static int[] num, idx;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new boolean[n];
        num = new int[n];
        idx = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        func(0, 0);
        System.out.println(sb);
    }

    public static void func(int depth, int start) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(num[idx[i]]).append(' ');
            }
            sb.append('\n');
            return;
        }
        int prev = 0;
        for (int i = start; i < n; i++) {
            int curr = num[i];
            if (curr == prev || check[i]) {
                continue;
            }
            idx[depth] = i;
            check[i] = true;
            func(depth + 1, i);
            check[i] = false;
            prev = curr;
        }
    }
}
