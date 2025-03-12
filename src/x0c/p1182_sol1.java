package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1182_sol1 {
    static int n, s, cnt;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        func(0, 0);
        if (s == 0) {
            cnt--;
        }
        System.out.println(cnt);
    }

    public static void func(int sum, int start) {
        if (sum == s) {
            cnt++;
        }
        for (int i = start; i < n; i++) {
            func(sum + num[i], i + 1);
        }
    }
}
