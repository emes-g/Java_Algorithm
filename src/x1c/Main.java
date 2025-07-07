package x1c;

/*
[input]
5 7
1 2 4
1 3 1
1 4 1
2 5 8
3 4 3
3 5 15
4 5 6

[output]
0	4	1	1	7
4	0	5	5	8
1	5	0	2	8
1	5	2	0	6
7	8	8	6	0

0	2	3	4	4
1	0	1	1	5
1	1	0	1	1
1	1	1	0	5
4	2	4	4	0

3 1 4 5
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000000;
    static int n, m;
    static int[][] dist, next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dist = new int[n + 1][n + 1];
        next = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dist[from][to] = dist[to][from] = weight;
            next[from][to] = to;
            next[to][from] = from;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];    // 단순히 k로 갱신하면 무결성 깨짐
                    }
                }
            }
//            System.out.printf("[%d]\n", k);
//            show(dist);
//            show(next);
        }
        show(dist);
        show(next);
        showPath(3, 5);
    }

    public static void show(int[][] arr) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%d\t", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void showPath(int start, int end) {
        int curr = start;
        while (curr != end) {
            System.out.printf("%d ", curr);
            curr = next[curr][end];
        }
        System.out.println(end);
    }
}
