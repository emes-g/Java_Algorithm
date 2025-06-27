package appendix_D;

// Basic에서 개선된 버전으로, 2가지만 신경쓰면 됨
// 1. 두 트리의 깊이(랭크)를 비교하고, 항상 u가 랭크가 높은 트리가 되도록
// 2. 랭크가 동일한 트리를 union하는 경우, u의 랭크를 1 증가

import java.util.Arrays;

public class UnionFind_UnionByRank implements UnionFind {
    int[] p;

    public UnionFind_UnionByRank(int[] p) {
        init(p);
    }

    @Override
    public void init(int[] p) {
        this.p = p;
        Arrays.fill(p, -1);
    }

    @Override
    public int find(int x) {    // Basic과 동일
        if (p[x] < 0) {
            return x;
        }
        return find(p[x]);
    }

    // 항상 u가 랭크가 높은 트리가 되도록
    @Override
    public boolean union(int u, int v) {    // 시간복잡도 : O(log(n))
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (p[u] > p[v]) { // 항상 u가 랭크가 높은 트리가 되도록, 스왑
            int temp = u;
            u = v;
            v = temp;
        }
        if (p[u] == p[v]) { // 랭크가 동일한 경우에만 1 증가
            p[u]--;
        }
        p[v] = u;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnionFind_UnionByRank : ");
        for (int i = 1; i < p.length; i++) {
            sb.append(p[i]).append('\t');
        }
        return sb.toString();
    }
}
