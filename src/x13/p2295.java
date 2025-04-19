package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class p2295 {
    static int n;
    static int[] arr;
    static ArrayList<Integer> two = new ArrayList<>();  // 두 요소의 합

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {   // O(n^2)
            for (int j = i; j < n; j++) {
                two.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(two);  // O(n^2 * logn)
        for (int i = n - 1; i >= 0; i--) {  // O(n^2 * logn)
            for (int j = 0; j < n; j++) {
                if (binarySearch(arr[i] - arr[j]) != -1) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }

    public static int binarySearch(int target) {
        int start = 0;
        int end = two.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (two.get(mid) == target) {
                return mid;
            } else if (two.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
