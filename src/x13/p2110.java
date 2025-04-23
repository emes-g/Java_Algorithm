package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2110 {
    static int n, c;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);   // O(nlogn)
        int start = 1;
        int end = arr[n - 1] - arr[0];
        while (start < end) {   // O(solve) * O(log(10억))
            int mid = (start + end + 1) / 2;
            if (solve(mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start);
    }

    // 결정 문제: 가장 인접한 두 공유기 사이의 거리가 x일 때, c개의 공유기를 설치할 수 있는지?
    public static boolean solve(int x) {    // O(nlogn)
        int curr = 0;   // 현재 설치한 집 번호
        int cnt = 1;    // 설치한 공유기의 개수
        while (true) {
            int next = lowerBound(arr[curr] + x);   // 다음에 설치할 집 번호
            if (next == n) {
                break;
            }
            curr = next;
            cnt++;
        }
        return cnt >= c;
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
