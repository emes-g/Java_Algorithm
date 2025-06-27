package appendix_D;

import java.util.Arrays;

public class UnionFind_Basic implements UnionFind {
    int[] p;

    public UnionFind_Basic(int[] p) {
        init(p);
    }

    @Override
    public void init(int[] p) {
        this.p = p;
        Arrays.fill(p, -1);
    }

    @Override
    public int find(int x) {    // 시간 복잡도 : O(n)
        if (p[x] < 0) { // 루트를 찾은 경우
            return x;
        }
        return find(p[x]);
    }

    @Override
    public boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {   // 이미 같은 그룹이었던 경우
            return false;
        }
        p[v] = u;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnionFind_Basic : ");
        for (int i = 1; i < p.length; i++) {
            sb.append(p[i]).append('\t');
        }
        return sb.toString();
    }
}
