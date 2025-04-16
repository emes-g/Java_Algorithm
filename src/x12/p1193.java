package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int n = 0;
        while (true) {
            int s = (n * (n - 1)) / 2;
            int e = (n * (n + 1)) / 2;
            if (x > s && x <= e) {
                int bj = 1;
                int bm = n;
                for (int i = s + 1; i < x; i++) {
                    bj++;
                    bm--;
                }
                if (n % 2 == 1) {   // swap
                    int temp = bj;
                    bj = bm;
                    bm = temp;
                }
                System.out.println(bj + "/" + bm);
                return;
            }
            n++;
        }
    }
}
