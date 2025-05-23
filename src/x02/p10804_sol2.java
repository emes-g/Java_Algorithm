package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 라이브러리 사용 X

public class p10804_sol2 {
    static final int SIZE = 20;
    static int[] arr = new int[SIZE + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= SIZE; i++) {
            arr[i] = i;
        }
        int t = 10;
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (from < to) {
                swap(from, to);
                from++;
                to--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= SIZE; i++) {
            sb.append(arr[i]).append(' ');
        }
        System.out.println(sb);
    }

    public static void swap(int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
