package x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class p1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] order = new int[n];
        int index = 0;

        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        ListIterator<Integer> cursor = list.listIterator();
        int step = 0;
        while (!list.isEmpty()) {
            while (cursor.hasNext()) {
                if (step == k - 1) {
                    order[index++] = cursor.next();
                    cursor.remove();
                    step = 0;
                    continue;
                }
                cursor.next();
                step++;
            }
            cursor = list.listIterator();
        }

        StringBuilder sb = new StringBuilder("<");
        for (int i = 0; i < n - 1; i++) {
            sb.append(order[i]).append(", ");
        }
        sb.append(order[n - 1]).append(">");
        System.out.println(sb);
    }
}
