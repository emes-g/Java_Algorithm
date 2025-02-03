package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2445 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < i; j++) {
                sb.append('*');
            }
            for (int k = 0; k < 2 * (n - i); k++) {
                sb.append(' ');
            }
            for (int j = 0; j < i; j++) {
                sb.append('*');
            }
            sb.append('\n');
        }
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                sb.append('*');
            }
            for (int k = 0; k < 2 * (n - i); k++) {
                sb.append(' ');
            }
            for (int j = 0; j < i; j++) {
                sb.append('*');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
