package x07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class p5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String cmd = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str.substring(1, str.length() - 1), ",");

            LinkedList<Integer> list = new LinkedList<>();
            while (st.hasMoreTokens()) {
                list.offer(Integer.parseInt(st.nextToken()));
            }

            boolean reverse = false;
            boolean error = false;
            for (int i = 0; i < cmd.length(); i++) {
                if (cmd.charAt(i) == 'R') {
                    reverse = !reverse;
                    continue;
                }
                if (list.isEmpty()) {
                    error = true;
                    break;
                }
                if (reverse) {
                    list.pollLast();
                } else {
                    list.poll();
                }
            }

            if (error) {
                System.out.println("error");
                continue;
            }
            if (list.isEmpty()) {
                System.out.println("[]");
                continue;
            }
            ListIterator<Integer> cursor;
            StringBuilder sb = new StringBuilder("[");
            if (reverse) {
                cursor = list.listIterator(list.size());
                sb.append(cursor.previous());
                while (cursor.hasPrevious()) {
                    sb.append(',').append(cursor.previous());
                }
            } else {
                cursor = list.listIterator();
                sb.append(cursor.next());
                while (cursor.hasNext()) {
                    sb.append(',').append(cursor.next());
                }
            }
            System.out.println(sb.append("]"));
        }
    }
}
