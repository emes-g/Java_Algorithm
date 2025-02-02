package x02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2490 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String result = "DCBAE";    // 함수 대신 문자열의 인덱스를 이용하도록 개선
        int i = 3;
        while (i-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int front = 0;
            while (st.hasMoreTokens()) {
                if (st.nextToken().equals("1")) {
                    front++;
                }
            }
            sb.append(result.charAt(front)).append('\n');
        }
        System.out.println(sb);
    }
}
