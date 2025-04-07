package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p11399 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 소요시간이 제일 적은 사람을 맨 앞에 둬야, 남은 사람들이 그만큼 덜 기다림
        Arrays.sort(arr);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (arr[i] * (n - i));
        }
        System.out.println(ans);
    }
}
