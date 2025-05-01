package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 그리디 + 우선순위 큐, O(nlogn)
// 앞에서부터 순회하면 컵라면을 더 많이 주는 문제를 놓칠 수 있고,
// 데드라인과 점수를 저장하는 자료구조 하나와 점수만을 기준으로 하는 우선순위 큐를 따로 둬야 함

public class p1781 {
    static int n;
    static Problem1781[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Problem1781[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            arr[i] = new Problem1781(deadline, cup);
        }
        Arrays.sort(arr);
        long ans = 0;
        int idx = 0;
        // 앞에서부터 순회하면 컵라면을 더 많이 주는 문제를 놓칠 수 있음
        for (int date = arr[0].deadline; date > 0; date--) {    // O(nlogn)
            while (idx < n && date == arr[idx].deadline) {
                pq.offer(arr[idx++].cup);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        System.out.println(ans);
    }
}

class Problem1781 implements Comparable<Problem1781> {
    int deadline, cup;

    public Problem1781(int deadline, int cup) {
        this.deadline = deadline;
        this.cup = cup;
    }

    // 데드라인 내림차순, 데드라인이 동일하면 컵라면 내림차순
    @Override
    public int compareTo(Problem1781 o) {
        if (this.deadline == o.deadline) {
            return o.cup - this.cup;
        }
        return o.deadline - this.deadline;
    }
}