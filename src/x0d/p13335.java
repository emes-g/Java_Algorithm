package x0d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
이벤트 기반 시뮬레이션 문제
- 현재는 이벤트 큐에 OUT만을 넣기 때문에 더 간소화할 수 있지만,
- 추후 이벤트 큐에 여러 이벤트를 넣는 문제를 고려하기 위해, 보다 범용적인 코드를 작성함

시간복잡도: O(NlogC)
- 단순 time++이 아니라, jump하는 방식으로 불필요한 루프를 최소화했음
- 루프는 2n번 가량 수행(모든 트럭의 입장+퇴장)
- 다만, 우선순위 큐가 항상 정렬 상태를 유지하기 위해 log의 시간복잡도를 사용하는 것을 고려
 */

public class p13335 {
    static final int OUT = 0, IN = 1;
    static int n, w, l;
    static int[] trucks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        trucks = new int[n];
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }
        int t = 1;
        int num = 0;    // 진입시킬 차량 번호
        int sum = 0;    // t 시점에서의 하중
        PriorityQueue<Event13335> pq = new PriorityQueue<>();
        while (num < n) {
            while (!pq.isEmpty()) {
                Event13335 peek = pq.peek();
                if (t < peek.time) {    // 현재 시점(t)에서 처리해 줄 이벤트가 없는 경우
                    break;
                }
                // 현재 시점(t)에 트럭이 빠지는 경우
                pq.poll();
                sum -= trucks[peek.num];
            }
            if (sum + trucks[num] > l) {    // 현재 시점(t)에 트럭이 진입할 수 없는 경우
                if (pq.isEmpty()) {
                    System.out.println("불가능: 다리의 하중보다 무거운 트럭이 등장한 경우");
                    return;
                }
                t = pq.peek().time;
                continue;
            }
            // 현재 시점(t)에 트럭이 진입할 수 있는 경우
            pq.offer(new Event13335(t + w, num, OUT));
            sum += trucks[num];
            num++;
            t++;
        }
        // 큐에 남은 이벤트 처리
        while (!pq.isEmpty()) {
            t = pq.poll().time;
        }
        System.out.println(t);
    }
}

class Event13335 implements Comparable<Event13335> {
    int time, num, type;

    public Event13335(int time, int num, int type) {
        this.time = time;
        this.num = num;
        this.type = type;
    }

    // 시간 오름차순으로 정렬
    // 시간이 동일하다면 OUT → IN 이벤트 순으로 처리
    @Override
    public int compareTo(Event13335 e) {
        if (this.time == e.time) {
            return this.type - e.type;
        }
        return this.time - e.time;  // 양수를 반환하면 스왑
    }
}