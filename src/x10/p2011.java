package x10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p2011 {
    static final int INVALID_PASSWORD = 0;
    static final int ONE_DIGIT = 1;
    static final int TWO_DIGIT = 2;
    static final int BOTH = 3;

    // dp[i] : i의 해석 가짓수
    static int[] password, dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        n = str.length();
        password = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            password[i] = str.charAt(i - 1) - '0';
        }
        if (password[1] == 0) {
            System.out.println(0);
            return;
        }
        dp = new int[n + 1];
        dp[0] = 1;  // 예외적
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            switch (decideValue(password[i - 1], password[i])) {
                case INVALID_PASSWORD:  // 암호 자체가 올바르지 않은 경우
                    System.out.println(0);
                    return;
                case ONE_DIGIT: // 암호의 현재 위치가 한 자릿수로만 해석되는 경우
                    dp[i] = dp[i - 1];
                    break;
                case TWO_DIGIT: // 암호의 현재 위치가 두 자릿수로만 해석되는 경우
                    dp[i] = dp[i - 2];
                    break;
                case BOTH:  // 둘 다 되는 경우
                    dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
                    break;
            }
        }
        System.out.println(dp[n]);
    }

    public static int decideValue(int front, int rear) {
        int x = (front * 10) + rear;
        if (rear == 0) {
            if (front == 1 || front == 2) {
                return TWO_DIGIT;
            } else {
                return INVALID_PASSWORD;
            }
        } else {
            if (x >= 11 && x <= 26) {
                return BOTH;
            } else {
                return ONE_DIGIT;
            }
        }
    }
}
