package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class p11286 {
    // 절댓값 기준 오름차순, 절댓값이 동일하다면 값 오름차순
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
        if (Math.abs(o1) == Math.abs(o2)) {
            return o1 - o2;
        }
        return Math.abs(o1) - Math.abs(o2);
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) {
                pq.add(x);
                continue;
            }
            if (pq.isEmpty()) {
                sb.append("0\n");
                continue;
            }
            sb.append(pq.poll()).append('\n');
        }
        System.out.println(sb);
    }
}
