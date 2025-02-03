package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2441 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int j;
            for (j = n; j > n - i; j--) {
                sb.append(' ');
            }
            for (; j > 0; j--) {
                sb.append('*');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
