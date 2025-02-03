package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < 9; i++) {
            list.add(Integer.parseInt(br.readLine()));
            total += list.get(i);
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (total - list.get(i) - list.get(j) == 100) {
                    for (int k = 0; k < 9; k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        sb.append(list.get(k)).append('\n');
                    }
                    System.out.println(sb);
                    return;
                }
            }
        }
    }
}
