package x12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int round = 1;
        while (n > 1) {
            a = (a + 1) / 2;    // ex. 19, 20번은 다음 라운드에서 10번이 되어야 하므로 ++하고 나누기 수행
            b = (b + 1) / 2;
            if (a == b) {
                System.out.println(round);
                return;
            }
            round++;
            n = (n + 1) / 2;    // 토너먼트를 진행하는 인원 수가 홀수인 경우 고려
        }
        System.out.println(-1);
    }
}
