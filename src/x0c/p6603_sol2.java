package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p6603_sol2 {
    static int k;
    static int[] num;
    static int[] arr = new int[6];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                System.out.println(sb);
                return;
            }
            num = new int[k];
            for (int i = 0; i < k; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(num);
            func(0, 0);
            sb.append('\n');
        }
    }

    public static void func(int depth, int idx) {
        if (depth == 6) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }
        if (idx == k) {
            return;
        }
        arr[depth] = num[idx];
        func(depth + 1, idx + 1);
        func(depth, idx + 1);
    }
}
