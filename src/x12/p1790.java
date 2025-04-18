package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1790 {
    static final int[] CNT = {0, 9, 180, 2700, 36000, 450000, 5400000, 63000000, 720000000, 9};    // i자릿수 개수
    static final int[] MIN = {0, 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};    // i자릿수 최솟값
    static int n, k, n_len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        n_len = String.valueOf(n).length();
        int len = getLength();  // 1부터 n까지 이어서 쓴 문자열의 길이
        if (len < k) {
            System.out.println(-1);
            return;
        }
        int num_len = 1;    // k번째 수를 요소로 갖는 수의 길이
        for (; k > CNT[num_len]; num_len++) {
            k -= CNT[num_len];
        }
        k -= 1;
        int quotient = k / num_len;
        int remainder = k % num_len;
        int num = MIN[num_len] + quotient;  // k번째 수를 요소로 갖는 수
        String str = String.valueOf(num);
        System.out.println(str.charAt(remainder));
    }

    public static int getLength() {
        int len = 0;    // 1부터 n까지 문자열을 이은 길이
        for (int i = 1; i < n_len; i++) {
            len += CNT[i];
        }
        len += ((n - MIN[n_len] + 1) * n_len);
        return len;
    }
}
