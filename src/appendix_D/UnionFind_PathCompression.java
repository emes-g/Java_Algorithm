package appendix_D;

// PathCompression이라고 소개를 했는데,
// UnionByRank에서 한 단계 더 나아간 버전임 (내 생각)
// 루트까지 올라가는 길에 방문한 모든 노드의 부모를 루트로 갱신
// (union은 그대로 두고, find 과정만 최적화)

import java.util.Arrays;

public class UnionFind_PathCompression implements UnionFind {
    int[] p;

    public UnionFind_PathCompression(int[] p) {
        init(p);
    }

    @Override
    public void init(int[] p) {
        this.p = p;
        Arrays.fill(p, -1);
    }

    @Override
    public int find(int x) {
        if (p[x] < 0) {
            return x;
        }
        return p[x] = find(p[x]);
    }

    @Override
    public boolean union(int u, int v) {    // 시간복잡도 : amortized O(α(n))
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (p[u] > p[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        if (p[u] == p[v]) {
            p[u]--;
        }
        p[v] = u;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UnionFind_PathCompression : ");
        for (int i = 1; i < p.length; i++) {
            sb.append(p[i]).append('\t');
        }
        return sb.toString();
    }
}
