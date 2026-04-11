package appendix_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
[외판원 순회에 필요한 최소 비용 출력]
앞서, 백트래킹을 통해 외판원 순회를 하게 되는 경우 (n-1)!로 TLE가 발생한다고 했다.
가장 큰 원인은 아무래도 계산했던 상태를 또 계산하는 상황이 발생하기 때문인데, 가령 현재 1→2→5를 방문한 상황이라 하자.
남은 정점들인 3,4를 어떻게 방문해야 할지는 모르겠지만, 1→2→5일때와 2→1→5일때 남은 정점들을 방문해야 하는 순서는 같을 것이다.
그렇기 때문에, 1→2→5에서 남은 정점들을 방문하는 순서를 이미 계산한 상황이라면, 2→1→5에서는 그 값을 가져다 쓰기만 사용하면 된다.
이는 DP에서 배웠던 메모이제이션(캐싱)을 활용하면 된다.

앞선 예제를 통해 dp[현재 위치][현재 방문한 정점 목록] 정도만 알고 있으면, 캐싱을 활용할 수 있다는 사실을 인지했다.
여기서 현재 위치는 그냥 정점 번호를 입력하면 될텐데, 현재 방문한 정점 목록은 어떻게 기억해야 할까?
현재 방문한 정점 목록의 경우의 수는 2^n개(1 to n번 정점의 방문 여부, n이 16이므로 정확히는 65536)이다.
각 경우의 수를 배열, 리스트 등의 자료구조로 관리하자니 메모리도 메모리지만, 무엇보다 현재 방문한 정점 목록을 확인하는데만 O(n)이 소요되는 문제가 발생한다.

그럼 현재 방문한 정점 목록을 O(1)에 확인할 수 있는 방법이 없을까?
정점 16개의 상태를 즉각적으로 확인할 수 있는 방법이 필요한데, 이때 비트마스킹이 사용될 수 있다.
비트마스킹을 이용하면 int형 변수 하나로 32개 수의 상태를, long형 변수 하나로 64개 수의 상태를 관리할 수 있다.
예시를 하나 들자면, 1→2→5든 2→1→5든 dp[5][11001]이라는 상태로 기억하면 된다.

이제 dp[현재 위치][현재 방문한 정점 목록]은 어떤 값으로 갱신되어야 할 지 알아보자.
앞선 예제에서 dp[5][11001]은 현재 5번 정점에 있고 [1, 2, 5]번 정점을 방문한 상태로,
남은 정점 [3, 4]를 방문하고 출발 정점으로 돌아가는 데 필요한 최소 비용이라는 값을 가져야 함을 알 수 있었다.
이를 점화식으로 정리하면, dp[현재][현재 방문 목록] = min(cost[현재][다음] + dp[다음][다음 방문 목록])가 된다.
초기값은 더 이상 방문할 정점이 없이, 각 정점에서 출발 정점으로 돌아가는데 필요한 최소 비용으로 정의하면 된다.
즉, dp[1 to 5][11111] 값을 출발점에 맞춰 적절히 정의하면 된다.
물론 문제 구조 상 사이클이 발생하기 때문에 임의의 점을 출발 정점으로 잡아도 상관 없어서, 나는 1번 정점을 출발 정점으로 잡고 구현할 것이다.

다음으로, dp를 탑다운으로 진행할지 바텀업으로 진행할지를 결정해보자.
탑다운이라고 하면, 최종적으로 알고 싶은 값에서 출발하여 초기값까지 파고드는 방식을 의미하는데, 재귀가 대표적인 구현 방식이다.
dp를 위해 점화식까지 고려했지만, 막상 바텀업으로 테이블을 채우려니 그 방법이 난감해서(비트마스크 상태 공간이 순차적이지 않기 때문) 나는 탑다운으로 진행할 것이다.
우리가 알고 싶은 dp[1][10000]이라는 값에서 출발하여, 초기값인 dp[1 to 5][11111]까지 파고 들면 된다.

요약하면, '비트마스킹을 통한 탑다운 DP'를 통해 문제를 해결할 것인데 시간복잡도는 어떻게 될까?
앞서, 백트래킹의 경우에는 (n-1)!이 소요되었지만, 결론부터 말하자면 해당 방식은 (n^2) * (2^n)에 해결된다. (n이 16이므로 넉넉히 가능)
DP에서 시간복잡도를 구하기 위해서 우리가 알아야 할 것은 2가지, 테이블의 크기(우리가 채워야 할 상태 개수)와 하나의 상태를 채우는 데 걸리는 시간이다.
테이블의 크기: n * 2^n (현재 정점의 개수가 n개이고, 방문할 수 있는 정점 목록의 경우의 수가 2^n이기 때문)
하나의 상태: n (아직 방문하지 않은 정점을 찾기 위해 모든 정점을 순회하기 때문)
 */

public class p2098 {

    static final int NOT_VISITED = -1;
    /*
    [불가능 상태를 기록해야 불필요한 탐색을 제거할 수 있음]
    가령, 1→2→5에서 남은 정점(3,4)을 방문할 수 있는 길이 없다고 하자.
    이때 불가능 상태를 기록하지 않으면, 2→1→5에서도 '남은 정점을 방문할 수 있는 길 존재 여부'를 파악하기 위해 불필요한 탐색을 수행하게 된다.
    따라서, 불가능 상태 역시 기록해둬야 한다.
     */
    static final int IMPOSSIBLE = Integer.MAX_VALUE / 2;
    static int n;
    static int[][] cost, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[n][(1 << n)];  // 정점이 n개고, 방문할 수 있는 정점 목록의 경우의 수가 2^n이기 때문
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], NOT_VISITED);
        }
        System.out.println(func(0, 1)); // 출발점 지정
    }

    public static int func(int curr, int state) {
        if (dp[curr][state] != NOT_VISITED) {   // 캐싱
            return dp[curr][state];
        }
        if (Integer.bitCount(state) == n) { // 초기값
            if (cost[curr][0] == 0) {   // 출발점으로의 길이 없는 경우
                return dp[curr][state] = IMPOSSIBLE;
            }
            return dp[curr][state] = cost[curr][0];
        }
        dp[curr][state] = IMPOSSIBLE;
        for (int i = 0; i < n; i++) {
            if ((state & (1 << i)) == 0 && cost[curr][i] != 0) {  // 아직 방문하지 않았고, 다음 도시로의 길이 있는 경우에만
                dp[curr][state] = Math.min(dp[curr][state], cost[curr][i] + func(i, state | (1 << i)));
            }
        }
        return dp[curr][state];
    }
}
