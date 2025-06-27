package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// dfs, O(v^2)

public class p1976_sol1 {
    static int n, m;    // n <= 200, m <= 1000
    static int[][] arr;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        group = new int[n + 1];
        Arrays.fill(group, -1);
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int groupNum = 0;
        for (int i = 1; i <= n; i++) {  // O(n^2)
            if (group[i] == -1) {
                dfs(i, groupNum++);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        while (st.hasMoreTokens()) {
            int to = Integer.parseInt(st.nextToken());
            if (group[from] != group[to]) {
                System.out.println("NO");
                return;
            }
            from = to;
        }
        System.out.println("YES");
    }

    public static void dfs(int curr, int groupNum) {
        group[curr] = groupNum;
        for (int next = 1; next <= n; next++) {
            if (arr[curr][next] == 0) {
                continue;
            }
            if (group[next] != -1) {
                continue;
            }
            dfs(next, groupNum);
        }
    }
}
