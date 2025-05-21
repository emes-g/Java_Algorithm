package x18;

import java.util.ArrayList;

// 그래프 사이클 탐지기
// 간선의 개수에 상관없이 사용가능
public interface CycleDetector {
    void init(ArrayList<ArrayList<Integer>> list);

    boolean hasCycle();

    void buildCyclePath(int start, int end);

    void showCyclePath();
}
