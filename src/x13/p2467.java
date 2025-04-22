package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2467 {
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
        int sum = Integer.MAX_VALUE;
        int a = -1;
        int b = -1;
        for (int i = 0; i < n; i++) {   // O(nlogn)
            int idx1 = lowerBound(arr[i] * -1); // 크기는 같고 부호가 반대인 자신의 하한(이상인 값 중 최소)을 찾음
            int idx2 = idx1 - 1;    // 자신의 하한-1(미만인 값 중 최대)을 찾음
            // 같은 용액은 2번 사용 불가
            if (idx1 == i) {
                idx1 += 1;
            }
            if (idx2 == i) {
                idx2 -= 1;
            }
            // 범위 내에 있는 경우만 진행
            if (idx1 >= 0 && idx1 < n) {
                if (Math.abs(arr[i] + arr[idx1]) < sum) {
                    sum = Math.abs(arr[i] + arr[idx1]);
                    a = i;
                    b = idx1;
                }
            }
            if (idx2 >= 0 && idx2 < n) {
                if (Math.abs(arr[i] + arr[idx2]) < sum) {
                    sum = Math.abs(arr[i] + arr[idx2]);
                    a = i;
                    b = idx2;
                }
            }
        }
        System.out.printf("%d %d\n", arr[a], arr[b]);
    }

    public static int lowerBound(int target) {
        int start = 0;
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
}
