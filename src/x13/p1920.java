package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 시간복잡도 : O(nlogn + mlogn)

public class p1920 {
    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);   // O(nlogn)
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (m-- > 0) {   // O(mlogn)
            int num = Integer.parseInt(st.nextToken());
            if (binarySearch(num) == -1) {
                sb.append(0).append('\n');
            } else {
                sb.append(1).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static int binarySearch(int x) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] < x) {
                start = mid + 1;
            } else if (arr[mid] == x) {
                return mid;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
