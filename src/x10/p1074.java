package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(func(r, c, n));
    }

    public static int func(int x, int y, int n) {
        if (n == 0) {
            return 0;
        }
        int half = 1 << (n - 1);
        if (x < half) {
            if (y < half) {
                return func(x, y, n - 1);
            }
            return half * half + func(x, y - half, n - 1);
        }
        if (y < half) {
            return half * half * 2 + func(x - half, y, n - 1);
        }
        return half * half * 3 + func(x - half, y - half, n - 1);
    }
}
