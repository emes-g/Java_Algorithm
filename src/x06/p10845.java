package x06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class p10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        Queue<Integer> q = new LinkedList<>();
        int last = -1;
        while (n-- > 0) {
            String cmd = br.readLine();
            if (cmd.equals("pop")) {
                if (q.isEmpty()) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(q.poll()).append('\n');
                }
            } else if (cmd.equals("size")) {
                sb.append(q.size()).append('\n');
            } else if (cmd.equals("empty")) {
                sb.append(q.isEmpty() ? 1 : 0).append('\n');
            } else if (cmd.equals("front")) {
                if (q.isEmpty()) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(q.peek()).append('\n');
                }
            } else if (cmd.equals("back")) {
                if (q.isEmpty()) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(last).append('\n');
                }
            } else {
                last = Integer.parseInt(cmd.split(" ")[1]);
                q.offer(last);
            }
        }
        System.out.println(sb);
    }
}
