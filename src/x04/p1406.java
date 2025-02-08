package x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class p1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int m = Integer.parseInt(br.readLine());

        List<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            list.add(str.charAt(i));
        }
        ListIterator<Character> cursor = list.listIterator(list.size());
        while (m-- > 0) {
            String cmd = br.readLine();
            if (cmd.equals("L")) {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                }
            } else if (cmd.equals("D")) {
                if (cursor.hasNext()) {
                    cursor.next();
                }
            } else if (cmd.equals("B")) {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }
            } else {
                char c = cmd.charAt(2);
                cursor.add(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);
        System.out.println(sb);
    }
}
