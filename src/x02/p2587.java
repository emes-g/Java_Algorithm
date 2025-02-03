package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        int i = 5;
        int total = 0;
        while (i-- > 0) {
            list.add(Integer.parseInt(br.readLine()));
            total += list.get(4 - i);
        }
        Collections.sort(list);
        System.out.println(total / 5);
        System.out.println(list.get(2));
    }
}
