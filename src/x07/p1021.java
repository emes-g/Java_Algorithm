package x07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class p1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int cnt = 0;
        while (m-- > 0) {
            int x = Integer.parseInt(st.nextToken());
            int idx = list.indexOf(x);
            int size = list.size();
            if (idx <= size / 2) {
                while (idx-- > 0) {
                    list.offer(list.poll());
                    cnt++;
                }
            } else {
                while (idx++ < size) {
                    list.offerFirst(list.pollLast());
                    cnt++;
                }
            }
            list.poll();
        }
        System.out.println(cnt);
    }
}

