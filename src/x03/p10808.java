package x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] counts = new int[26];
        for (int i = 0; i < input.length(); i++) {
            counts[input.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int count : counts) {
            sb.append(count).append(' ');
        }
        System.out.println(sb);
    }
}
