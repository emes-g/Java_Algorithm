package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 항상 lower의 size가 higher 이상인 상태에서
// 두 큐의 조건을 맞춰주고, lower.peek() 출력

public class p1655_sol2 {
    static PriorityQueue<Integer> lower = new PriorityQueue<>((o1, o2) -> o2 - o1);    // 중간 이하 최대 힙
    static PriorityQueue<Integer> higher = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (lower.size() == higher.size()) {
                lower.offer(num);
            } else {
                higher.offer(num);
            }
            if (!higher.isEmpty() && lower.peek() > higher.peek()) {
                higher.offer(lower.poll());
                lower.offer(higher.poll());
            }
            sb.append(lower.peek()).append('\n');
        }
        System.out.println(sb);
    }
}
