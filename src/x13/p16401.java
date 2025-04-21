package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p16401 {
    static int m, n;
    static int[] sticks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        sticks = new int[n];
        for (int i = 0; i < n; i++) {
            sticks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sticks);    // O(nlogn)
        int start = 1;
        int end = 1000000000;
        while (start < end) {   // O(30 * logn)
            int mid = (start + end + 1) / 2;
            if (solve(mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        if (solve(start)) {
            System.out.println(start);
        } else {    // 모든 조카에게 과자를 나눠줄 수 없는 경우
            System.out.println(0);
        }
    }

    public static boolean solve(int x) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += sticks[i] / x;
        }
        return cnt >= m;
    }
}
