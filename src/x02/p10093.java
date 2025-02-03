package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class p10093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Long> list = new ArrayList<>();
        list.add(Long.parseLong(st.nextToken()));
        list.add(Long.parseLong(st.nextToken()));
        Collections.sort(list);

        if (list.get(0).equals(list.get(1))) {
            System.out.println(0);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(1) - list.get(0) - 1).append('\n');
        for (long i = list.get(0) + 1; i < list.get(1); i++) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }
}
