package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1676 {
    // 아래와 같이 제출하긴 했지만,
    // 사실 언제나 5의 개수는 2의 개수 이하기 때문에,
    // 2에 대해서는 계산해줄 필요가 없음
    static int two, five;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 2; i <= n; i++) {
            int temp = i;
            while (temp % 2 == 0) {
                two++;
                temp /= 2;
            }
            while (temp % 5 == 0) {
                five++;
                temp /= 5;
            }
        }
        System.out.println(Math.min(two, five));
    }
}