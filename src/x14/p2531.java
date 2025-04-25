package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터, O(n)

public class p2531 {
    static int n, d, k, c, ans;
    static int[] arr;
    static int[] used = new int[3001];  // [start, end) 구간에서의 초밥 종류별 사용 횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n * 2];   // 벨트 확장
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = arr[n + i] = num;
        }
        int cnt = 0;    // [start, end) 구간에 있는 서로 다른 초밥 개수
        int end = 0;
        for (int start = 0; start < n; start++) {
            while (end - start < k) {
                if (used[arr[end]] == 0) {
                    cnt++;
                }
                used[arr[end]]++;
                end++;
            }
            // 연속해서 k개를 먹은 경우
            ans = Math.max(ans, used[c] == 0 ? cnt + 1 : cnt);
            used[arr[start]]--;
            if (used[arr[start]] == 0) {
                cnt--;
            }
        }
        System.out.println(ans);
    }
}
