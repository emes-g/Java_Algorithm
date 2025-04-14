package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1978 {
    static boolean[] isPrime = new boolean[1001];
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < 1001; i++) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime[i] = false;
                    break;
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (isPrime[num]) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
