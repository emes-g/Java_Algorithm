package x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class p5397 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            String str = br.readLine();
            List<Character> list = new LinkedList<>();
            ListIterator<Character> cursor = list.listIterator();

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '<') {
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                    }
                } else if (c == '>') {
                    if (cursor.hasNext()) {
                        cursor.next();
                    }
                } else if (c == '-') {
                    if (cursor.hasPrevious()) {
                        cursor.previous();
                        cursor.remove();
                    }
                } else {
                    cursor.add(c);
                }
            }
            StringBuilder sb = new StringBuilder();
            list.forEach(sb::append);
            System.out.println(sb);
        }
    }
}
