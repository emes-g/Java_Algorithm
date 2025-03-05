package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p11729 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        sb.append((1 << n) - 1).append('\n');
        run(1, 3, n);
        System.out.println(sb);
    }

    public static void run(int from, int to, int n) {
        if (n == 1) {
            sb.append(from).append(' ').append(to).append('\n');
            return;
        }
        run(from, 6 - from - to, n - 1);
        sb.append(from).append(' ').append(to).append('\n');
        run(6 - from - to, to, n - 1);
    }
}