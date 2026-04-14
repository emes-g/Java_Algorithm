package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
## 2차원 DP 풀이: 어떤 과정을 통해 2차원 DP로 해결하게 되었는지

무게로든 가치로든 담을 수 있다고 무작정 담는게 최적이 아님 → 그리디 아님

dp[i] = i번째 물건까지 고려할 때 가치의 최댓값 → 무게에 대한 정보가 전무해서 문제를 해결할 수 없음
몇 번째 물건까지 고려할 것인지 뿐만 아니라, 무게에 대한 정보도 담고 있어야 함

dp[i][j] = dp[물건 번호][무게]라고 했을 때, 전체 라운드는 1000만 개가 됨 (100 * 10만, 공간적으로 40mb)
그럼 각 라운드를 O(1)에만 해결하면 되는데, 테이블의 정의는 아래와 같이 4개가 될 수 있음

dp[i][j] = i번째 물건을 담고, 가방 최대 용량이 j일 때 가치의 최댓값
dp[i][j] = i번째 물건을 담고, 가방 용량이 정확히 j일 때 가치의 최댓값
dp[i][j] = i번째 물건까지 고려하고, 가방 최대 용량이 j일 때 가치의 최댓값
dp[i][j] = i번째 물건까지 고려하고, 가방 용량이 정확히 j일 때 가치의 최댓값

dp라는 것은 결국 최적의 부분 구조(optimal substructure)를 만족해야 하는데, 무슨 말이냐면
현재 상태를 계산하기 위해서 이전 상태를 이용해야 한다는 것에 있음

근데 i번째 물건을 담는다고 정의해버리면, 애초에 i번째 물건을 못 담는 경우를 정의할 수 없음 → 'i번째 물건까지 고려하는 것'으로 결정
또한, 가방 용량이 정확히 j일 때를 기준으로 하면, dp[n][1~k]까지 전부 봐야하는 반면, 최대 용량을 기준으로 하면 dp[n][k]만 보면 됨.
나아가, 아래에서 설명하겠지만, 아래 점화식이 성립하지 않게 됨
(정확히 가방 용량이 j-items[i].cost인 경우가 없을 확률이 다분하기 때문)

결국 'dp[i][j] = i번째 물건까지 고려하고, 가방 최대 용량이 j일 때 가치의 최댓값'으로 정의를 마쳤으니,
이제 점화식을 살펴보자. 결국 i번째 물건을 담지 않는 경우와 담는 경우로 나뉜다.
dp[i][j] = max(dp[i-1][j], dp[i-1][j-items[i].weight] + items[i].value)

초기값을 지정해줘야 할텐데.
결국 우리가 아는건 dp[1][items[1].weight ~ k]의 값이 items[1].value라는 것임

---

## 1차원 풀이: 2차원 풀이에서 라운드(dp[i][j]) 갱신에 현재 행과 직전 행만을 사용한다는 것에서 착안

여기까지 하면 2차원 배열을 이용한 풀이는 끝나는데, dp[i][j] 값을 갱신하는 과정을 지켜보면 i-2, i-3번째 행 등 먼 과거의 정보를 보는 것이 아니라, 현재 행(i)과 직전 행(i-1)만 참조하는 것을 확인할 수 있다.
이는 곧, '1차원 배열로 축소할 수 있겠다'는 생각으로 이어질 수 있다.

테이블의 정의 자체는 'i번째 물건까지 고려하고, 가방 최대 용량이 j일 때 가치의 최댓값'으로 변함 없지만, 1차원 배열(dp[j])을 이용해서 구현해보자.
점화식은 dp[j] = max(dp[j], dp[j-items[i].weight] + items[i].value)라고 하고 실제로 테이블을 채워보자.

이때 반례를 보다 명확히 확인하기 위해 기존 (3, 6) 아이템을 (3, 7) 아이템으로 변경해볼 것이다.
i=3일때 우리는 (0, 0, 7, 8, 8, 13, 15)가 나오길 기대했는데, 위 점화식을 통해 돌려보면 13이 아니라 14가 나오는 것을 확인할 수 있다.
이는 dp[j-items[i].weight]가 i=2일때의 값을 가리키는 것이 아니라, i=3일때 이미 갱신된 값을 가리키고 있기 때문이다.
즉, 현재 아이템 (3, 7)을 여러 번(여기서는 두 번) 사용하는 문제가 발생한 것이다.
이를 해결하기 위해서는(현재 아이템을 한 번만 사용하기 위해서는) i=3일때 dp[3]보다 dp[6]이 먼저 갱신되어야 함을 알 수 있고, 이를 확장하면 정방향이 아닌 역방향으로 탐색해야 한다는 사실로 이어질 수 있을 것이다.

한편, 2차원일때와는 다르게 1차원이 기존 정보를 갖고 있기 때문에 전 구간(k to 1)이 아니라, 부분 구간(k to items[i].weight)까지만 수행하면 된다.
 */

public class p12865_sol2 {

    static int n, k;
    static Pair12865[] items;
    static int[] dp;  // dp[j]: i번째 물건까지 고려하고, 가방 최대 용량이 j일 때 가치의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        items = new Pair12865[n + 1];
//        items[0] = new Pair12865(0, 0);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            items[i] = new Pair12865(weight, value);
        }
        dp = new int[k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = k; j >= items[i].weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - items[i].weight] + items[i].value);
            }
        }
        System.out.println(dp[k]);
    }
}

/*
class Pair12865 {
    int weight, value;

    public Pair12865(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
*/