package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int e = Integer.parseInt(st.nextToken());   // [1, 15]
        int s = Integer.parseInt(st.nextToken());   // [1, 28]
        int m = Integer.parseInt(st.nextToken());   // [1, 19]
        int year = e;
        while (true) {
            // 세 자연수의 최소공배수까지만 봐도 됨
            if (year % 28 == s % 28 && year % 19 == m % 19) {
                break;
            }
            year += 15;
        }
        System.out.println(year);
    }
}
