package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1929 {
    static final int MAX = 1000001;
    static int n, m;
    static boolean[] isPrime = new boolean[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i < MAX; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < MAX; j += i) {  // j = i * 2로 시작해도 되긴 함 (간단하긴 함)
                isPrime[j] = false;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n; i <= m; i++) {
            if (isPrime[i]) {
                sb.append(i).append('\n');
            }
        }
        System.out.println(sb);
    }
}