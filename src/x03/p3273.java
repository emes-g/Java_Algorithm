package x03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class p3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        int x = Integer.parseInt(br.readLine());

        boolean[] occur = new boolean[2000001];
        for (int num : list) {
            occur[num] = true;
        }
        int count = 0;
        for (int num : list) {
            if (x < num || num * 2 == x) {
                continue;
            }
            if (occur[num] && occur[x - num]) {
                count++;
                occur[num] = false;
            }
        }
        System.out.println(count);
    }
}
