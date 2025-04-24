package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2003 {
    static int n, m, ans;
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
        int end = 0;
        int sum = 0;    // [start, end) 합
        for (int start = 0; start < n; start++) {   // O(n)
            while (end < n && sum + arr[end] < m) { // 끝까지 가든지, [start, end] 합이 m 이상이 될 때까지
                sum += arr[end];
                end++;
            }
            if (end == n) {
                break;
            }
            if (sum + arr[end] == m) {
                ans++;
            }
            sum -= arr[start];
        }
        System.out.println(ans);
    }
}
