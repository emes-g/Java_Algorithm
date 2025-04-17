package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// sol1 : n번째 감소하는 수만 정확히 도출 (규칙 도출)
// sol2 : 모든 감소하는 수를 순서에 상관없이 찾아두고, 정렬해서 답을 도출 (직관적)

public class p1038_sol2 {
    static int n;
    static List<Long> nums = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (n <= 10) {
            System.out.println(n);
            return;
        }
        if (n >= 1023) {
            System.out.println(-1);
            return;
        }
        for (int i = 0; i < 10; i++) {
            func(1, i);
        }
        Collections.sort(nums);
        System.out.println(nums.get(n));
    }

    // 수의 범위를 감소하면서 진행
    public static void func(int length, long prefix) {
        if (length > 10) {
            return;
        }
        nums.add(prefix);
        int lastNum = (int) (prefix % 10);
        for (int i = 0; i < lastNum; i++) {
            func(length + 1, prefix * 10 + i);
        }
    }
}
