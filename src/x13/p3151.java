package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 2295(세 수의 합)번 문제랑 다른 점을 체크하며 풀어보기
// 이 문제에서는 "시작과 끝을 지정해주는 이분탐색도 존재함"을 알고 가면 됨

public class p3151 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        // 러프하게 반복문이 n^2이고, 어떤 값이 n개까지 존재할 수 있으니까
        // ans는 n^3까지 될 수 있다고 생각하고 long으로 선언
        long ans = 0;
        for (int i = 0; i < n; i++) {   // O(n^2 * logn)
            for (int j = i + 1; j < n; j++) {
                int cnt = upperBound(j + 1, -arr[i] - arr[j]) - lowerBound(j + 1, -arr[i] - arr[j]);
                ans += cnt;
            }
        }
        System.out.println(ans);
    }

    public static int lowerBound(int start, int target) {
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int upperBound(int start, int target) {
        int end = arr.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}