package x17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class p2075 {
    static int n;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                pq.offer(Integer.valueOf(st.nextToken()));
            }
        }
        for (int i = 1; i < n; i++) {
            pq.poll();
        }
        System.out.println(pq.poll());
    }
}