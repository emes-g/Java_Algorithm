package x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2577 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());
        String str = String.valueOf(A * B * C);
        int[] counts = new int[10];

        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - '0']++;
        }
        for (int count : counts) {
            System.out.println(count);
        }
    }
}
