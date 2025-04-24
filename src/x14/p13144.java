package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 투 포인터, O(n)

public class p13144 {
    static int n;
    static int[] arr;
    static boolean[] check = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        long ans = 0;
        int start = 0;
        int end = 0;
        while (start < n) { // O(n)
            while (end < n && !check[arr[end]]) {   // 끝까지 가든지, 중복된 수를 발견하기 전까지
                check[arr[end]] = true;
                end++;
                ans++;
            }
            if (end == n) {
                while (start < end) {   // [start + 1, n), [start + 2, n), ..., [n- 1, n)을 고려해줘야 함
                    start++;
                    ans += (end - start);
                }
                break;
            }
            // 중복된 수를 발견한 경우 시작점을 갱신,
            check[arr[start]] = false;
            start++;
            // 이때 누락이 발생하지 않도록 보정 작업 필요
            ans += (end - start);
        }
        System.out.println(ans);
    }
}
