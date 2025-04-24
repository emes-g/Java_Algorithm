package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터, O(n)

public class p1806 {
    static final int MAX = 100000;
    static int n, s;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = MAX;
        int end = 0;
        int sum = 0;    // [start, end) 합
        for (int start = 0; start < n; start++) {   // O(n)
            while (end < n && sum + arr[end] < s) { // 끝까지 가든가, [start, end] 합이 s 이상이 될 때까지
                sum += arr[end];
                end++;
            }
            if (end == n) {
                break;
            }
            ans = Math.min(ans, end - start + 1);
            sum -= arr[start];
        }
        if (ans == MAX) {
            System.out.println(0);
            return;
        }
        System.out.println(ans);
    }
}
