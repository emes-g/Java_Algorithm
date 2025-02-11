package x07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class p10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Deque<Integer> dq = new ArrayDeque<>();
        while (n-- > 0) {
            String cmd = br.readLine();
            if (cmd.startsWith("push_front")) {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                dq.offerFirst(x);
            } else if (cmd.startsWith("push_back")) {
                int x = Integer.parseInt(cmd.split(" ")[1]);
                dq.offer(x);
            } else if (cmd.equals("pop_front")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append('\n');
                    continue;
                }
                sb.append(dq.poll()).append('\n');
            } else if (cmd.equals("pop_back")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append('\n');
                    continue;
                }
                sb.append(dq.pollLast()).append('\n');
            } else if (cmd.equals("size")) {
                sb.append(dq.size()).append('\n');
            } else if (cmd.equals("empty")) {
                sb.append(dq.isEmpty() ? 1 : 0).append('\n');
            } else if (cmd.equals("front")) {
                if (dq.isEmpty()) {
                    sb.append(-1).append('\n');
                    continue;
                }
                sb.append(dq.peek()).append('\n');
            } else {
                if (dq.isEmpty()) {
                    sb.append(-1).append('\n');
                    continue;
                }
                sb.append(dq.peekLast()).append('\n');
            }
        }
        System.out.println(sb);
    }
}