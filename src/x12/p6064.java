package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int ans = x;
            int lcm = lcm(m, n);
            for (; ans <= lcm; ans += m) {
                if (ans % n == y % n) { // y로 하면 y == n인 경우는 고려하지 못함
                    break;
                }
            }
            if (ans > lcm) {
                ans = -1;
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    public static int gcd(int a, int b) {
        if (a < b) {    // swap
            int temp = b;
            b = a;
            a = temp;
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
