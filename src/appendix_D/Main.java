package appendix_D;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // UnionFind_Basic
        UnionFind uf = new UnionFind_Basic(new int[11]);
        uf.union(1, 5);
        System.out.println(uf);
        uf.union(2, 3);
        System.out.println(uf);
        uf.union(1, 3);
        System.out.println(uf);
        uf.union(2, 6);
        System.out.println(uf);
        System.out.println();

        // UnionFind_UnionByRank
        uf = new UnionFind_UnionByRank(new int[11]);
        uf.union(1, 5);
        System.out.println(uf);
        uf.union(2, 3);
        System.out.println(uf);
        uf.union(4, 3);
        System.out.println(uf);
        uf.union(1, 4);
        System.out.println(uf);
        System.out.println();

        // UnionFind_PathCompression
        uf = new UnionFind_PathCompression(new int[11]);
        uf.union(1, 5);
        System.out.println(uf);
        uf.union(2, 3);
        System.out.println(uf);
        uf.union(4, 3);
        System.out.println(uf);
        uf.union(1, 4);
        System.out.println(uf);
        System.out.println();
    }
}
