package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p7453 {
    static int n;
    static int[] A, B, C, D, two;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];
        two = new int[n * n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                two[idx++] = C[i] + D[j];
            }
        }
        Arrays.sort(two);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int target = -(A[i] + B[j]);
                int cnt = upperBound(target) - lowerBound(target);
                ans += cnt;
            }
        }
        System.out.println(ans);
    }

    public static int lowerBound(int target) {
        int start = 0;
        int end = two.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (two[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int upperBound(int target) {
        int start = 0;
        int end = two.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (two[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
