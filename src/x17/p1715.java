package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 우선순위 큐, O(nlogn)

public class p1715 {
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {   // O(nlogn)
            int num = Integer.parseInt(br.readLine());
            pq.offer(num);
        }
        long ans = 0;
        while (pq.size() > 1) { // O(nlogn)
            int cnt = pq.poll() + pq.poll();
            ans += cnt;
            pq.offer(cnt);
        }
        System.out.println(ans);
    }
}
