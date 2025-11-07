package x04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// LinkedList를 직접 사용한 것은 아니고, 아이디어 착안

public class p1158_sol2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder("<");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] prev = new int[n + 1];
        int[] next = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        prev[1] = n;
        next[n] = 1;
        int curr = n;
        int removeCnt = 0;  // 원에서 제거한 사람 수
        int goCnt = 0;  // 전진 횟수
        while (removeCnt != n - 1) {
            while (goCnt++ != k) {  // k번 전진
                curr = next[curr];
            }
            // curr번 노드 기록 (ArrayList에 추가해놓고 마지막에 출력해도 되긴 함)
            sb.append(curr).append(", ");
            // curr번 노드 unlink
            next[prev[curr]] = next[curr];
            prev[next[curr]] = prev[curr];
            removeCnt++;
            goCnt = 0;
        }
        sb.append(next[curr]).append('>');
        System.out.println(sb);
    }
}
