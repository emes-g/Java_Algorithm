package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터, O(n)

public class p22862 {
    static int n, k, ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) % 2;  // 홀짝만 중요
        }
        int cnt = 0;    // [start, end) 구간의 홀수 개수
        int end = 0;
        for (int start = 0; start < n; start++) {
            while (end < n) {
                if (arr[end] == 1) {
                    if (cnt >= k) { // 이미 구간에 k개의 홀수가 있는데, 또 홀수가 등장한 경우
                        break;
                    }
                    cnt++;
                }
                end++;
            }
            int length = end - start - cnt; // 연속한 짝수 부분 수열 길이
            if (end == n) {
                ans = Math.max(ans, length);
                break;
            }
            // 이미 구간에 k개의 홀수가 있는데, 또 홀수가 등장한 경우
            ans = Math.max(ans, length);
            if (arr[start] == 1) {
                cnt--;
            }
        }
        System.out.println(ans);
    }
}
