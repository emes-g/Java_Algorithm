package appendix_D;

public interface UnionFind {
    void init(int[] p);
    int find(int x);
    boolean union(int u, int v);
}
