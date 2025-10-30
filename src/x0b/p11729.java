package x0b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
1. 함수 정의
func(n, from, to) : 원반 n개를 from에서 to로 옮기는 방법을 출력

2. base cond.
n == 0 : 원판이 없으므로 아무 행동 X

3. 재귀식 정의
원판이 1개일 때, 원판을 내가 원하는 곳으로 옮길 수 있다.
원판이 k개일 때 옮길수 있으면, (k+1)개일 때도 옮길 수 있다.
f(k+1, from, to) = f(k, from, 6-from-to) + (k+1)번째 옮김 + f(k, 6-from-to, to)

+) 시간복잡도
점화식으로 구할 수는 있긴 한데, 이 문제에서는 출력하는 횟수로 판정해도 될 듯
 */

public class p11729 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        sb.append((1 << n) - 1).append('\n');
        func(n, 1, 3);
        System.out.println(sb);
    }

    public static void func(int n, int from, int to) {
        if (n == 0) {   // 원반이 없으므로 아무것도 안함
            return;
        }
        func(n - 1, from, 6 - from - to);    // n-1개의 원반을 from에서 temp로
        sb.append(from).append(' ').append(to).append('\n');    // 1개의 원반을 from에서 to로
        func(n - 1, 6 - from - to, to);    // n-1개의 원반을 temp에서 to로
    }
}