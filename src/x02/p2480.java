package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2480 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        int price;
        if (x == y && y == z) {
            price = 10000 + x * 1000;
        } else if (x == y) {
            price = 1000 + x * 100;
        } else if (y == z || x == z) {
            price = 1000 + z * 100;
        } else {
            int max = Math.max(Math.max(x, y), z);
            price = max * 100;
        }
        System.out.println(price);
    }
}
