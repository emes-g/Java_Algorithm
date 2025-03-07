package x0b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(run(a, b, c));
    }

    public static long run(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }
        long val = run(a, b / 2, c);
        val = val * val % c;
        if (b % 2 == 0) {
            return val;
        } else {
            return val * a % c;
        }
    }
}
