package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1541_sol2 {
    // 덧셈을 먼저 계산하고, (부분합을 먼저 생성해주고)
    // 간편하게 빼버리는 방식
    // ex.
    // 30-70-20+40-10+100+30-35
    // 30-(70)-(20+40)-(10+100+30)-35
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        String[] nums = st.nextToken().split("[+]");
        for (String num : nums) {
            sum += Integer.parseInt(num);
        }
        while (st.hasMoreTokens()) {
            nums = st.nextToken().split("[+]");
            for (String num : nums) {
                sum -= Integer.parseInt(num);
            }
        }
        System.out.println(sum);
    }
}
