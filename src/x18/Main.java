package x18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
[let]
- 무방향 그래프

[input]
5
7
3 1
2 3
4 1
5 2
5 4
3 5
2 4
 */

public class Main {
    static int n;
    static int[][] arr;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> directed = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            directed.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = arr[to][from] = 1;
            list.get(from).add(to);
            list.get(to).add(from);
            directed.get(from).add(to);
        }
        // BFS
        arrayBFS(1);
        listBFS(1);

        // 재귀 DFS
        Arrays.fill(visited, false);    // 재귀 DFS 전 방문 배열 초기화
        System.out.print("재귀 DFS : ");
        recursiveDFS(1);
        System.out.println();

        // 비재귀 DFS
        nonRecursiveDFS(1);

        // 무향 그래프 사이클 탐지
        CycleDetector cd = new CycleDetector_Undirected(list);
        if (cd.hasCycle()) {
            System.out.println("사이클 탐지!");
            cd.showCyclePath();
        } else {
            System.out.println("사이클 없음");
        }

        // 유향 그래프 사이클 탐지
        cd = new CycleDetector_Directed(directed);
        if (cd.hasCycle()) {
            System.out.println("사이클 탐지!");
            cd.showCyclePath();
        } else {
            System.out.println("사이클 없음");
        }
    }

    // 인접 행렬 BFS
    public static void arrayBFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visited, false);
        visited[start] = true;
        q.offer(start);
        System.out.printf("인접 행렬 BFS : %d ", start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next = 1; next <= n; next++) {
                if (arr[curr][next] == 0) {
                    continue;
                }
                if (visited[next]) {
                    continue;
                }
                System.out.printf("%d ", next);
                visited[next] = true;
                q.offer(next);
            }
        }
        System.out.println();
    }

    // 인접 리스트 BFS
    public static void listBFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(visited, false);
        visited[start] = true;
        q.offer(start);
        System.out.printf("인접 리스트 BFS : %d ", start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int next : list.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                System.out.printf("%d ", next);
                visited[next] = true;
                q.offer(next);
            }
        }
        System.out.println();
    }

    // 재귀 DFS
    public static void recursiveDFS(int curr) {
        visited[curr] = true;
        System.out.printf("%d ", curr);
        for (int next : list.get(curr)) {
            if (visited[next]) {
                continue;
            }
            recursiveDFS(next);
        }
    }

    // 비재귀 DFS
    public static void nonRecursiveDFS(int start) {
        Stack<Integer> s = new Stack<>();
        Arrays.fill(visited, false);
        s.push(start);
        System.out.print("비재귀 DFS : ");
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            System.out.printf("%d ", curr);
            // 논리적으로는 맞으나, 스택이므로 재귀 DFS와 순서가 달라질 수 있음
            for (int next : list.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                s.push(next);
            }
//            // 재귀 DFS와 순서까지 동일하게 하려면?
//            ArrayList<Integer> neighbors = list.get(curr);
//            for (int i = neighbors.size() - 1; i >= 0; i--) {
//                int next = neighbors.get(i);
//                if (visited[next]) {
//                    continue;
//                }
//                s.push(next);
//            }
        }
        System.out.println();
    }
}
