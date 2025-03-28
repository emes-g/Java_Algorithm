package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class p2217 {
    static int n;
    // 로프를 (N-1)개 사용했을 때 최적해를, 로프를 N개 사용했을 때 최적해를 구하는 과정에서 사용하지 않음
    // 즉, 최적 부분 구조를 갖지 않으므로 DP로 해결할 수 없음
    static Integer[] ropes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ropes = new Integer[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes, Collections.reverseOrder()); // 내림차순 정렬
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, ropes[i] * (i + 1));
        }
        System.out.println(ans);
    }
}
