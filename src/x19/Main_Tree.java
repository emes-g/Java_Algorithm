package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
[트리]
- 무방향이면서 사이클이 없는 연결 그래프
- 정점이 V개면 간선은 (V-1)개
- 임의의 두 정점을 연결하는 경로가 유일함

[input]
8
1 2
2 5
1 3
1 4
4 6
6 7
6 8
 */

public class Main_Tree {
    static int n;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] parent, depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        parent = new int[n + 1];
        depth = new int[n + 1];
        for (int i = 1; i < n; i++) {   // 정점이 n개이므로 간선은 (n-1)개
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        // BFS
//        listBFS(1);
        listBFSWithDepth(1);

        // 재귀 DFS
        System.out.print("재귀 DFS : ");
        Arrays.fill(parent, 0);
        Arrays.fill(depth, 0);
        recursiveDFS(1);
        System.out.println();
        showDepth();

        // 비재귀 DFS
        // 관념적으로 알고 있는 DFS로 구현할 수는 없음
        // 단순히 큐만 스택으로 바꾸는 형태만 가능하므로, 별도로 구현 X
    }

    // 트리 BFS
    // 이진 트리에서의 레벨 순회
    public static void listBFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(parent, 0);
        q.offer(start);
        System.out.printf("트리 BFS : %d ", start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : list.get(curr)) {
                if (parent[curr] == next) {    // 부모 노드인 경우
                    continue;
                }
                parent[next] = curr;
                q.offer(next);
                System.out.printf("%d ", next);
            }
        }
        System.out.println();
    }

    // 트리 BFS
    // 모든 노드의 깊이 계산까지
    public static void listBFSWithDepth(int start) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(parent, 0);
        Arrays.fill(depth, 0);
        depth[start] = 0;
        q.offer(start);
        System.out.printf("트리 BFS : %d ", start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : list.get(curr)) {
                if (parent[curr] == next) { // 부모 노드인 경우
                    continue;
                }
                parent[next] = curr;
                depth[next] = depth[curr] + 1;
                q.offer(next);
                System.out.printf("%d ", next);
            }
        }
        System.out.println();
        showDepth();
    }

    // 트리 재귀 DFS
    // 이진 트리에서의 전위 순회
    public static void recursiveDFS(int curr) {
        System.out.printf("%d ", curr);
        for (int next : list.get(curr)) {
            if (parent[curr] == next) { // 부모 노드인 경우
                continue;
            }
            parent[next] = curr;
            depth[next] = depth[curr] + 1;
            recursiveDFS(next);
        }
    }

    public static void showDepth() {
        System.out.print("트리의 깊이 : ");
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d ", depth[i]);
        }
        System.out.println();
    }
}
