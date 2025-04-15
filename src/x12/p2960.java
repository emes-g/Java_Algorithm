package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2960 {
    static int n, k, cnt;
    static boolean[] isPrime = new boolean[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            if (++cnt == k) {
                System.out.println(i);
                return;
            }
            for (int j = i * 2; j <= n; j += i) {
                if (!isPrime[j]) {
                    continue;
                }
                isPrime[j] = false;
                if (++cnt == k) {
                    System.out.println(j);
                    return;
                }
            }
        }
    }
}
