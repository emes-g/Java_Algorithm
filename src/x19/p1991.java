package x19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이진 트리 순회

public class p1991 {
    static int n;
    static char[] lc, rc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lc = new char[n + 1];
        rc = new char[n + 1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = st.nextToken().charAt(0) - 'A' + 1;
            lc[p] = st.nextToken().charAt(0);
            rc[p] = st.nextToken().charAt(0);
        }
        preorderTraversal('A');
        sb.append('\n');
        inorderTraversal('A');
        sb.append('\n');
        postorderTraversal('A');
        System.out.println(sb);
    }

    public static void preorderTraversal(char c) {
        sb.append(c);
        int curr = c - 'A' + 1;
        if (lc[curr] != '.') {
            preorderTraversal(lc[curr]);
        }
        if (rc[curr] != '.') {
            preorderTraversal(rc[curr]);
        }
    }

    public static void inorderTraversal(char c) {
        int curr = c - 'A' + 1;
        if (lc[curr] != '.') {
            inorderTraversal(lc[curr]);
        }
        sb.append(c);
        if (rc[curr] != '.') {
            inorderTraversal(rc[curr]);
        }
    }

    public static void postorderTraversal(char c) {
        int curr = c - 'A' + 1;
        if (lc[curr] != '.') {
            postorderTraversal(lc[curr]);
        }
        if (rc[curr] != '.') {
            postorderTraversal(rc[curr]);
        }
        sb.append(c);
    }
}
