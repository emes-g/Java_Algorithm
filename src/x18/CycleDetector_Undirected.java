package x18;

import java.util.ArrayList;
import java.util.Collections;

// 무향 그래프 사이클 탐지기
public class CycleDetector_Undirected implements CycleDetector {
    private ArrayList<ArrayList<Integer>> list;
    private int n;  // 노드 개수
    private boolean[] visited;
    private int[] parent;
    private ArrayList<Integer> cyclePath;

    public CycleDetector_Undirected(ArrayList<ArrayList<Integer>> list) {
        init(list);
    }

    @Override
    public void init(ArrayList<ArrayList<Integer>> list) {
        this.list = list;
        this.n = list.size() - 1;
        this.visited = new boolean[n + 1];
        this.parent = new int[n + 1];
        this.cyclePath = new ArrayList<>();
    }

    @Override
    public boolean hasCycle() {
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                if (dfs(i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int curr, int p) {
        visited[curr] = true;
        parent[curr] = p;
        for (int next : list.get(curr)) {   // 연결된 모든 간선에 대하여
            if (!visited[next]) {
                if (dfs(next, curr)) {
                    return true;
                }
                continue;
            }
            if (next != p) {    // 방문한 노드를 재방문하는 새로운 간선을 발견한 경우
                buildCyclePath(next, curr);
                return true;
            }
        }
        return false;
    }

    @Override
    public void buildCyclePath(int start, int end) {
        cyclePath.add(end);
        int curr = end;
        while (curr != start) {
            curr = parent[curr];
            cyclePath.add(curr);
        }
        Collections.reverse(cyclePath);
    }

    @Override
    public void showCyclePath() {
        System.out.print("사이클 경로 : ");
        for (int node : cyclePath) {
            System.out.printf("%d ", node);
        }
        System.out.println();
    }
}
