package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p10815 {
    static int n, k;
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
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while (k-- > 0) {   // O(klogn)
            int num = Integer.parseInt(st.nextToken());
            if (binarySearch(num) == -1) {
                sb.append(0).append(' ');
            } else {
                sb.append(1).append(' ');
            }
        }
        System.out.println(sb);
    }

    public static int binarySearch(int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }
        return -1;
    }
}
