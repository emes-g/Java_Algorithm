package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 트리의 순회, O(V)

public class p15681 {
    static int n;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] parent;
    static int[] size;  // i번 정점을 루트로 하는 서브트리의 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        parent = new int[n + 1];
        size = new int[n + 1];
        Arrays.fill(size, 1);
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        dfs(r);
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            int num = Integer.parseInt(br.readLine());
            sb.append(size[num]).append('\n');
        }
        System.out.println(sb);
    }

    public static void dfs(int curr) {
        for (int next : list.get(curr)) {
            if (parent[curr] == next) {
                continue;
            }
            parent[next] = curr;
            dfs(next);
            size[curr] += size[next];
        }
    }
}
