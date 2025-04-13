package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class p1744_sol2 {
    static PriorityQueue<Integer> neg = new PriorityQueue<>();
    static PriorityQueue<Integer> pos = new PriorityQueue<>(Collections.reverseOrder());
    static boolean hasZero;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if (num < 0) {
                neg.offer(num);
            } else if (num == 0) {
                hasZero = true;
            } else if (num == 1) {
                ans++;
            } else {
                pos.offer(num);
            }
        }
        while (!neg.isEmpty()) {
            if (neg.size() == 1) {
                if (!hasZero) {
                    ans += neg.poll();
                }
                break;
            }
            ans += (neg.poll() * neg.poll());
        }
        while (!pos.isEmpty()) {
            if (pos.size() == 1) {
                ans += pos.poll();
                break;
            }
            ans += (pos.poll() * pos.poll());
        }
        System.out.println(ans);
    }
}
