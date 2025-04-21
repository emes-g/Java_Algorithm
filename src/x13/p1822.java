package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1822 {
    static int na, nb;
    static int[] a, b;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        na = Integer.parseInt(st.nextToken());
        nb = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = new int[na];
        for (int i = 0; i < na; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        st = new StringTokenizer(br.readLine());
        b = new int[nb];
        for (int i = 0; i < nb; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b);
        for (int i = 0; i < na; i++) {
            if (binarySearch(a[i]) == -1) {
                list.add(a[i]);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');
        for (Integer num : list) {
            sb.append(num).append(' ');
        }
        System.out.println(sb);
    }

    public static int binarySearch(int target) {
        int start = 0;
        int end = b.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (b[mid] == target) {
               return mid;
            } else if (b[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
