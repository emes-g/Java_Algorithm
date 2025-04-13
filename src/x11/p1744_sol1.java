package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class p1744_sol1 {
    static int n, neg, zero, one, pos, ans;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            if (nums[i] < 0) {
                neg++;
            } else if (nums[i] == 0) {
                zero++;
            } else if (nums[i] == 1) {
                one++;
            } else {
                pos++;
            }
        }
        Arrays.sort(nums);
        if (neg % 2 == 0) {
            for (int i = 0; i < neg; i += 2) {
                ans += (nums[i] * nums[i + 1]);
            }
        } else {
            for (int i = 0; i < neg - 1; i += 2) {
                ans += (nums[i] * nums[i + 1]);
            }
            if (zero == 0) {
                ans += nums[neg - 1];
            }
        }
        ans += one; // 1은 합에 사용해야함
        int posIdx = neg + zero + one;
        if (pos % 2 != 0) { // 1을 초과하는 자연수가 홀수개 있으면, 그중 가장 작은 자연수는 합에 사용
            ans += nums[posIdx++];
        }
        for (int i = posIdx; i < n; i += 2) {
            ans += (nums[i] * nums[i + 1]);
        }
        System.out.println(ans);
    }
}
