package x08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int bar = 1;
        int total = 0;
        for (int i = 1; i < str.length(); i++) {
            char prev = str.charAt(i - 1);
            char curr = str.charAt(i);
            if (curr == '(') {
                bar++;
            } else {
                bar--;
                if (prev == '(') {
                    total += bar;
                } else {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}
