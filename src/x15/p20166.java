package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 백트래킹 + 해쉬맵, O(8^5 * nm)

public class p20166 {
    static int n, m;
    static char[][] board;
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = str.charAt(j - 1);
            }
        }
        // O(nm) * O(8^5)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                func("", 0, i, j);
            }
        }
        StringBuilder sb = new StringBuilder();
        int k = Integer.parseInt(st.nextToken());
        while (k-- > 0) {
            String str = br.readLine();
            if (map.containsKey(str)) {
                sb.append(map.get(str)).append('\n');
                continue;
            }
            sb.append(0).append('\n');
        }
        System.out.println(sb);
    }

    // prefix의 길이가 length일 때, 수행할 행동
    public static void func(String prefix, int length, int x, int y) {
        if (length == 5) {
            return;
        }
        String str = prefix + board[x][y];
        if (!map.containsKey(str)) {
            map.put(str, 0);
        }
        map.put(str, map.get(str) + 1);
        int[] nx = nx(x);
        int[] ny = ny(y);
        for (int i = 0; i < 3; i++) {   // O(8^5)
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                func(str, length + 1, nx[i], ny[j]);
            }
        }
    }

    public static int[] nx(int x) {
        int[] nx = new int[3];
        nx[0] = (x == 1 ? n : x - 1);
        nx[1] = x;
        nx[2] = (x == n ? 1 : x + 1);
        return nx;
    }

    public static int[] ny(int y) {
        int[] ny = new int[3];
        ny[0] = (y == 1 ? m : y - 1);
        ny[1] = y;
        ny[2] = (y == m ? 1 : y + 1);
        return ny;
    }
}