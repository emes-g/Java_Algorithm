package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    [parametric search]
    - 최적화 문제를 결정 문제로 바꿔 이분탐색을 수행
    - 아래 문제를 예시로 들어보면,
    - 최적화 문제 : n개의 로프로 들어올릴 수 있는 물체의 최대 중량
    - 결정 문제 : 물체의 중량이 x일 때, n개의 로프로 들어올릴 수 있는가?
    - 물체의 중량(x)이 증가할 때, 로프의 개수(y)가 증감이 확정되지는 않지만
        즉, 속성 그래프(y축이 개수와 같은 속성)에서 단조성이 보장되지는 않지만
    - 물체의 중량(x)이 증가할 때, 'n개의 로프로 들 수 있는가?'에 대한 답(y)의 단조성은 보장되므로,
        즉, 결정 그래프의 단조성이 보장되므로
    - 매개변수 탐색을 적용할 수 있다.
 */

public class p2217_sol2 {
    static int n;
    static int[] ropes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ropes = new int[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ropes); // 이분탐색을 위해 정렬
        int start = 0;
        int end = Integer.MAX_VALUE / 2;
        while (start < end) {
            int mid = (start + end + 1) / 2;    // 무한루프 방지 (분기 처리가 구간을 절반으로 나누지 못하므로 +1)
            if (solve(mid)) {   // 물체의 중량이 mid일 때, n개의 로프로 들 수 있다면
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    // 결정 문제 : 물체의 중량이 x일 때, 최대 N개의 로프로 들어올릴 수 있는가?
    public static boolean solve(int x) {
        for (int i = 1; i <= n; i++) {
            int canLift = i * ropes[n - i];
            if (canLift >= x) {
                return true;
            }
        }
        return false;
    }
}
