package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2473 {
    static final long MAX = 5000000000L;
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
        Arrays.sort(arr);   // O(nlogn)
        long ans = MAX;
        int a = -1;
        int b = -1;
        int c = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int target = (arr[i] + arr[j]) * -1;
                int idx1 = lowerBound(target);  // 이상인 값 중 최소
                int idx2 = idx1 - 1;    // 미만인 값 중 최대
                // 같은 용액은 2번 사용 불가
                while (idx1 == i || idx1 == j) {
                    idx1++;
                }
                while (idx2 == i || idx2 == j) {
                    idx2--;
                }
                // 범위 내에 있는 경우만 진행
                if (idx1 >= 0 && idx1 < n) {
                    long sum = (long) arr[i] + arr[j] + arr[idx1];  // 우변 캐스팅해야 함
                    if (Math.abs(sum) < ans) {
                        ans = Math.abs(sum);
                        a = i;
                        b = j;
                        c = idx1;
                    }
                }
                if (idx2 >= 0 && idx2 < n) {
                    long sum = (long) arr[i] + arr[j] + arr[idx2];
                    if (Math.abs(sum) < ans) {
                        ans = Math.abs(sum);
                        a = i;
                        b = j;
                        c = idx2;
                    }
                }
            }
        }
        // 오름차순 출력
        int[] temp = new int[]{arr[a], arr[b], arr[c]};
        Arrays.sort(temp);
        System.out.printf("%d %d %d\n", temp[0], temp[1], temp[2]);
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
