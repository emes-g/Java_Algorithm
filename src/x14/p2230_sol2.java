package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 투 포인터, O(nlogn)

public class p2230_sol2 {
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
        int end = 0;
        for (int start = 0; start < n; start++) {   // O(n)
            while (end < n && arr[end] - arr[start] < m) {
                end++;
            }
            if (end == n) {
                break;
            }
            ans = Math.min(ans, arr[end] - arr[start]);
        }
        System.out.println(ans);
    }
}
