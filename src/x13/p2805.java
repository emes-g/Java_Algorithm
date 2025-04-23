package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2805 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int start = 0;
        int end = 1000000000;
        while (start < end) {   // O(nlogn)
            int mid = (start + end + 1) / 2;
            if (solve(mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    public static boolean solve(int x) {
        long sum = 0;   // 오버플로우 주의
        for (int i = 0; i < n; i++) {
            sum += Math.max(arr[i] - x, 0);
        }
        return sum >= m;
    }
}
