package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    [parametric search]
    - 최적화 문제를 결정 문제로 바꿔 이분탐색을 수행
    - 아래 문제를 예시로 들어보면,
    - 최적화 문제 : m개의 휴게소를 짓고 난 후에, '휴게소가 없는 구간의 최댓값'의 최솟값
    - 결정 문제 : '휴게소가 없는 구간의 최댓값'이 x일 때, m개의 휴게소만으로 커버할 수 있는가? (가정에 모순이 안 나타나면 됨)
    - 최솟값을 구하는 매개변수 탐색과 최댓값을 구하는 매개변수 탐색이 완전히 다르네.
 */

public class p1477 {
    static int n, m, l;
    static int[] rests;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        if (n != 0) {   // 기존에 설치된 휴게소가 있는 경우
            rests = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                rests[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(rests); // 이분탐색을 위해 정렬
        }
        int start = 1;
        int end = 1000;
        while (start < end) {
            int mid = (start + end) / 2;
            if (solve(mid)) {   // '휴게소가 없는 구간의 최댓값'이 x일 때, m개의 휴게소만으로 커버할 수 있다면
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    // '휴게소가 없는 구간의 최댓값'이 mid일 때, m개의 휴게소만으로 커버할 수 있는가?
    public static boolean solve(int x) {
        int cnt = 0;    // '휴게소가 없는 구간의 최댓값'이 mid일 때, 설치해야 할 휴게소의 최소 개수 (따라서 cnt <= m일 때 m개의 휴게소로 커버 가능)
        int prev = 0;   // 이전 휴게소의 위치
        // 1. [0, 마지막 휴게소] 구간
        for (int i = 0; i < n; i++) {
            int gap = rests[i] - prev;  // (prev, rests[i]) 구간에 설치 가능 → (rests[i]-prev-1)개 설치 가능
            cnt += (gap - 1) / x;   // while문으로 curr만큼 증감을 반복하는 대신, 몫으로 한 번에 계산 가능 (★)
            prev = rests[i];
        }
        // 2. [마지막 휴게소, l] 구간
        int gap = l - prev;
        cnt += (gap - 1) / x;
        return cnt <= m;
    }
}