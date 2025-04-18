package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] bj = {getPrimeDivisorCnt(n, 2), getPrimeDivisorCnt(n, 5)};
        int[] bm1 = {getPrimeDivisorCnt(m, 2), getPrimeDivisorCnt(m, 5)};
        int[] bm2 = {getPrimeDivisorCnt(n - m, 2), getPrimeDivisorCnt(n - m, 5)};
        int two = Math.max(bj[0] - bm1[0] - bm2[0], 0);
        int five = Math.max(bj[1] - bm1[1] - bm2[1], 0);
        System.out.println(Math.min(two, five));
    }

    // O(log x)에 소인수 개수 반환
    public static int getPrimeDivisorCnt(int x, int primeDivisor) {
        int cnt = 0;
        long divisor = primeDivisor;
        while (x / divisor != 0) {
            cnt += (int) (x / divisor);
            divisor *= primeDivisor;
        }
        return cnt;
    }
}
