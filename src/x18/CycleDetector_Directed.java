package x18;

import java.util.ArrayList;
import java.util.Collections;

// 유향 그래프 사이클 탐지기
public class CycleDetector_Directed implements CycleDetector {
    final int NOT_VISITED = 0;
    final int VISITING = 1;
    final int VISITED = 2;

    private ArrayList<ArrayList<Integer>> list;
    private int n;  // 노드 개수
    private int[] status, parent;
    private ArrayList<Integer> cyclePath;

    public CycleDetector_Directed(ArrayList<ArrayList<Integer>> list) {
        init(list);
    }

    @Override
    public void init(ArrayList<ArrayList<Integer>> list) {
        this.list = list;
        this.n = list.size() - 1;
        this.status = new int[n + 1];
        this.parent = new int[n + 1];
        this.cyclePath = new ArrayList<>();
    }

    @Override
    public boolean hasCycle() {
        for (int i = 1; i <= n; i++) {
            if (status[i] == NOT_VISITED) {
                if (dfs(i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int curr, int p) {
        status[curr] = VISITING;
        parent[curr] = p;
        for (int next : list.get(curr)) {   // 연결된 모든 간선에 대하여
            if (status[next] == NOT_VISITED) {
                if (dfs(next, curr)) {
                    return true;
                }
                continue;
            }
            if (status[next] == VISITING) { // 이번 시행에서 방문한 노드를 재방문하는 경우
                buildCyclePath(next, curr);
                return true;
            }
        }
        status[curr] = VISITED;
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
