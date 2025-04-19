package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p18870_sol1 {
    static int n;
    static int[] org, sorted;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        org = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            org[i] = Integer.parseInt(st.nextToken());
        }
        sorted = Arrays.copyOf(org, n);
        Arrays.sort(sorted);    // O(nlogn)
        int num = 0;
        for (int i = 0; i < n; i += upperBound(sorted[i]) - lowerBound(sorted[i])) {    // O(nlogn)
            map.put(sorted[i], num++);
        }
        for (int i = 0; i < n; i++) {   // O(n)
            sb.append(map.get(org[i])).append(' ');
        }
        System.out.println(sb);
    }

    public static int lowerBound(int target) {
        int start = 0;
        int end = sorted.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (sorted[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int upperBound(int target) {
        int start = 0;
        int end = sorted.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (sorted[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
