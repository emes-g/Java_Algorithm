package x12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        divisor(36);
        System.out.println(gcd(20, 12));
        System.out.println(lcm(20, 12));
    }

    // [약수 구하기]
    public static void divisor(int n) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (i * i == n) {
                nums.add(i);
            } else if (n % i == 0) {
                nums.add(i);
                nums.add(n / i);
            }
        }
        Collections.sort(nums);
        for (Integer num : nums) {
            System.out.printf("%d ", num);
        }
    }

    // 최대공약수 구하기
    // gcd(a, b) == gcd(b, a % b)임을 이용
    // b가 0이 될 때, a가 최대공약수
    public static int gcd(int a, int b) {
        if (a < b) {    // swap
            int temp = b;
            b = a;
            a = temp;
        }
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 최소공배수 구하기
    // a * b = gcd(a, b) * lcm(a, b)
    // 오버플로우 방지를 위해, 나누기 먼저 수행
    public static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
