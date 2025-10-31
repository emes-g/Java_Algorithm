package x0b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 귀납적 사고
n=0일때 r행c열을 몇번째로 방문했는지 알수있다.
n=k일때 알수있으면, n=k+1일때도 알수있다.

2. 함수
f(n,r,c) = 2^n * 2^n 배열에서 r행c열을 몇번째로 방문하는지를 반환하는 함수
(2,3,1) -> (1,1,1) -> (0)
(3,7,7) -> (2,3,3) -> (1,1,1) -> (0)

3. base cond.
n이 0일때 r,c는 무조건 0이 되어 첫번째로 방문하므로 0 반환
if (n == 0) -> return 0

4. 재귀식 구성
half = (1<<n-1)
if (r < half, c < half)     -> return f(n-1,r,c)
if (r < half, c >= half)    -> return half^2 + f(n-1,r,c-half)
if (r >= half, c < half)    -> return 2 * half^2 + f(n-1,r-half,c)
if (r >= half, c >= half)   -> return 3 * half^2 + f(n-1,r-half,c-half)
 */

public class p1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(func(n, r, c));
    }

    public static int func(int n, int r, int c) {
        if (n == 0) {
            return 0;
        }
        int half = 1 << (n - 1);
        if (r < half) {
            if (c < half) {
                return func(n - 1, r, c);
            }
            return half * half + func(n - 1, r, c - half);
        } else {
            if (c < half) {
                return half * half * 2 + func(n - 1, r - half, c);
            }
            return half * half * 3 + func(n - 1, r - half, c - half);
        }
    }
}
