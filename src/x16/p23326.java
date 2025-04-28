package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

// TreeSet, O(qlogn)

public class p23326 {
    static int n;
    static TreeSet<Integer> set = new TreeSet<>();  // 명소 위치 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            if (st.nextToken().equals("1")) {
                set.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        int curr = 1;
        while (q-- > 0) {   // O(qlogn)
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("1")) {
                int num = Integer.parseInt(st.nextToken());
                if (set.contains(num)) {
                    set.remove(num);
                } else {
                    set.add(num);
                }
            } else if (cmd.equals("2")) {
                int x = Integer.parseInt(st.nextToken());
                curr = (curr + x) % n;
                if (curr == 0) {
                    curr = n;
                }
            } else {
                if (set.ceiling(curr) != null) {
                    sb.append(set.ceiling(curr) - curr).append('\n');
                    continue;
                }
                if (!set.isEmpty()) {
                    sb.append(n - curr + set.first()).append('\n');
                    continue;
                }
                sb.append(-1).append('\n');
            }
        }
        System.out.println(sb);
    }
}
