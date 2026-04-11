package appendix_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
[외판원 순회에 필요한 최소 비용 출력]
n이 16인데, 일부러 (n-1)!으로 동작하는 코드를 작성해 TLE 발생하도록 유도했음.

아래 코드(현재)는 모든 경우의 수((n-1)!)에 대하여 O(1)에 최솟값 갱신읋 하는 방식인데, 해당 방식에서는 중복되는 연산이 발생함.
가령 현재 내가 3번 정점에 위치해 있고, (1, 2, 3)번 정점을 방문한 상황이라 하자.
내가 1 → 2 → 3으로 방문했든, 2 → 1 → 3으로 방문했든 결국 내가 이후의 정점들을 최소의 비용으로 방문하는 상황 자체는 1개로 동일하다.
근데, 모든 경우의 수를 확인하는 작업에서는 해당 상황이 발생할 때마다 계산을 해줘야 한다.
우리는 이 과정(문제)을 메모이제이션(캐싱)을 통해 해결하려고 한다.
 */

public class p2098_fail {

    static int n;
    static int[][] cost;
    static boolean[] check;
    static int[] state;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n + 1][n + 1];
        check = new boolean[n + 1];
        state = new int[n];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 어차피 사이클이 형성되므로 시작 정점을 어디로 하든 상관없음
        int start = 1;
        check[start] = true;
        state[0] = start;
        func(1, 0, start);
        System.out.println(ans);
    }

    public static void func(int level, int sum, int prev) {
        if (level == n) {
            if (cost[prev][state[0]] != 0) {
                ans = Math.min(ans, sum + cost[prev][state[0]]);
            }
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (check[i]) {
                continue;
            }
            if (cost[prev][i] == 0) {   // 길이 없는 경우
                continue;
            }
            check[i] = true;
            state[level] = i;
            func(level + 1, sum + cost[prev][i], i);
            check[i] = false;
        }
    }
}
