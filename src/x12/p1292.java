package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1292 {
    static final int MAX = 1000;
    static int[] arr = new int[MAX + 1];
    static int a, b, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        fillArr();
        for (int i = a; i <= b; i++) {
            ans += arr[i];
        }
        System.out.println(ans);
    }

    public static void fillArr() {
        int idx = 1;
        for (int i = 1; idx <= MAX; i++) {
            for (int j = 0; j < i && idx <= MAX; j++) {
                arr[idx++] = i;
            }
        }
    }
}
