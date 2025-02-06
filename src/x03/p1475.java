package x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1475 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] counts = new int[10];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            counts[c - '0']++;
        }
        int max = 0;
        for (int i = 0; i < counts.length; i++) {
            if (i != 6 && i != 9) {
                max = Math.max(max, counts[i]);
            }
        }
        max = Math.max(max, (counts[6] + counts[9] + 1) / 2);
        System.out.println(max);
    }
}
