package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터, O(n)

public class p20922 {
    static int n, k, ans;
    static int[] arr;
    static int[] used = new int[100001];    // [start, end) 구간에서의 해당 숫자 등장 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int end = 0;
        for (int start = 0; start < n; start++) {   // O(n)
            while (end < n) {
                if (used[arr[end]] >= k) {  // 이미 해당 숫자가 k번 등장했는데, 또 등장한 경우
                    break;
                }
                used[arr[end]]++;
                end++;
            }
            ans = Math.max(ans, end - start);
            used[arr[start]]--;
        }
        System.out.println(ans);
    }
}
