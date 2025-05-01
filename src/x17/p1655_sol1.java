package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

// 우선순위 큐 2개 이용, O(nlogn)
// 논리 흐름대로 풀긴 했는데, 개선할 여지가 많은 코드

public class p1655_sol1 {
    static PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());    // 중간 이하 값을 내림차순으로 저장
    static PriorityQueue<Integer> higher = new PriorityQueue<>();   // 중간 이상 값을 오름차순으로 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        if (n == 1) {
            System.out.println(br.readLine());
            return;
        }
        // while문에서 매번 isEmpty() 비교할 때 발생하는 성능 저하 때문에 빼놓긴 했는데,
        // 미미하므로 이렇게 따로 빼놓을 필요 없음
        n -= 2;
        int first = Integer.parseInt(br.readLine());
        int second = Integer.parseInt(br.readLine());
        sb.append(first).append('\n');
        if (first < second) {
            lower.offer(first);
            higher.offer(second);
            sb.append(first).append('\n');
        } else {
            lower.offer(second);
            higher.offer(first);
            sb.append(second).append('\n');
        }
        // 분기를 빡세게 나누긴 했는데, 개선할 여지가 있음
        while (n-- > 0) {   // O(nlogn)
            int num = Integer.parseInt(br.readLine());
            if (lower.size() == higher.size()) {
                if (num <= lower.peek()) {
                    lower.offer(num);
                    sb.append(lower.peek()).append('\n');
                } else {
                    higher.offer(num);
                    sb.append(higher.peek()).append('\n');
                }
                continue;
            }
            if (lower.size() > higher.size()) {
                if (num <= lower.peek()) {
                    lower.offer(num);
                    higher.offer(lower.poll());
                } else {
                    higher.offer(num);
                }
                sb.append(Math.min(lower.peek(), higher.peek())).append('\n');
                continue;
            }
            if (num <= lower.peek()) {
                lower.offer(num);
            } else {
                higher.offer(num);
                lower.offer(higher.poll());
            }
            sb.append(Math.min(lower.peek(), higher.peek())).append('\n');
        }
        System.out.println(sb);
    }
}
