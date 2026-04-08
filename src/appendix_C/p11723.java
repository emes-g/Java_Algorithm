package appendix_C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비트마스킹 사용법 익히기

public class p11723 {

    static int state;   // 32개의 수를 int 변수 하나로 관리 가능

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int x = Integer.parseInt(st.nextToken());
                state |= (1 << (x - 1));
            } else if (cmd.equals("remove")) {
                int x = Integer.parseInt(st.nextToken());
                state &= ~(1 << (x - 1));
            } else if (cmd.equals("check")) {
                int x = Integer.parseInt(st.nextToken());
                if ((state & (1 << (x - 1))) != 0) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (cmd.equals("toggle")) {
                int x = Integer.parseInt(st.nextToken());
                state ^= (1 << (x - 1));
            } else if (cmd.equals("all")) {
                state = (1 << 20) - 1;
            } else {    // empty
                state = 0;
            }
            // 만약 현재 집합(state)에 포함된 '원소의 개수'를 알고 싶다면 Integer.bitCount(state)
        }
        System.out.println(sb);
    }
}
