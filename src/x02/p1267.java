package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p1267 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int y = 0, m = 0;
        for (int i = 0; i < n; i++) {
            y += (list.get(i) / 30 + 1) * 10;
            m += (list.get(i) / 60 + 1) * 15;
        }
        if (y == m) {
            System.out.printf("Y M %d", y);
        } else if (y < m) {
            System.out.printf("Y %d", y);
        } else {
            System.out.printf("M %d", m);
        }
    }
}
