package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1026 {
    static int n;
    // 재배열 부등식 문제
    // 재배열 부등식을 모르더라도, 잘 모르겠는 경우 정렬하고 이리저리 끼워 맞추는 방식을 시도해 볼 수 있음
    static int[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (a[i] * b[n - 1 - i]);
        }
        System.out.println(ans);
    }
}
