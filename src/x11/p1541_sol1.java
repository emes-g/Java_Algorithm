package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p1541_sol1 {
    static int n, sum, partSum;
    static int[] nums = new int[26];
    static char[] signs = new char[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for (String s : str.split("[+\\-]")) {
            nums[n++] = Integer.parseInt(s);
        }
        for (int i = 0, index = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '+' || c == '-') {
                signs[index++] = c;
            }
        }
        // 최초로 음수가 등장하기 전까지 더함
        sum = nums[0];
        int startIdx = 1;
        while (signs[startIdx] == '+') {
            sum += nums[startIdx++];
        }
        // 양수는 계속 부분합으로 누적하고, 음수가 등장하면 부분합 초기화
        for (int i = startIdx; i < n; i++) {
            if (signs[i] == '+') {
                partSum += nums[i];
            } else {
                sum -= partSum;
                partSum = nums[i];
            }
        }
        System.out.println(sum - partSum);
    }
}
