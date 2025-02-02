package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 7;
        int min = 100;
        int total = 0;
        while (i-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num % 2 == 1) {
                total += num;
                min = Math.min(num, min);
            }
        }
        if (total == 0) {
            System.out.println("-1");
            return;
        }
        System.out.println(total);
        System.out.println(min);
    }
}
