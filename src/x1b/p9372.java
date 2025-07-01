package x1b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// O(T * m)

public class p9372 {
    static int n, m;    // n <= 1000, m <= 10000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < m; i++) {
                br.readLine();
            }
            // 연결 그래프 == 모든 정점은 연결되어 있고, 간선은 최소 (n-1)개 존재
            // 가장 적은 종류의 비행기를 타는 경우 == 신장트리를 구성하는 간선만을 이용해 이동하는 경우
            sb.append(n - 1).append('\n');
        }
        System.out.println(sb);
    }
}
