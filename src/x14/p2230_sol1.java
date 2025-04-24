package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이분탐색, O(nlogn)

public class p2230_sol1 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);   // O(nlogn)
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {   // O(nlogn)
            int lower = lowerBound(i, arr[i] + m);
            if (lower == n) {
                break;
            }
            ans = Math.min(ans, arr[lower] - arr[i]);
        }
        System.out.println(ans);
    }

    public static int lowerBound(int start, int target) {
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
