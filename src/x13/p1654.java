package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    [parametric search]
    - 최적화 문제를 결정 문제로 바꿔 이분탐색을 수행
    - 아래 문제를 예시로 들어보면,
    - 최적화 문제 : n개의 랜선을 만들 수 있는 최대 길이
    - 결정 문제 : 랜선의 길이가 x일 때 n개의 랜선을 만들 수 있는지 없는지
    - 랜선의 길이(x)가 증가할 때, 랜선의 개수(y)는 감소하므로,
        즉 감소함수이므로 parmetric search 가능
 */

public class p1654 {
    static int n, k;
    static int[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        lines = new int[k];
        for (int i = 0; i < k; i++) {
            lines[i] = Integer.parseInt(br.readLine());
        }
        long start = 1;
        long end = (1L << 31) - 1;
        while (start < end) {   // 후보가 1개가 될 때까지
            long mid = (start + end + 1) / 2;   // 무한루프 방지 (mid가 영역을 절반으로 나눌 수 있도록)
            if (solve(mid)) {   // n개를 만들 수 있는 경우
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);  // 후보가 1개가 되었으므로 (start == end이므로) 아무거나 출력
    }

    public static boolean solve(long x) {
        long cnt = 0;
        for (int i = 0; i < k; i++) {
            cnt += (lines[i] / x);
        }
        return cnt >= n;
    }
}
