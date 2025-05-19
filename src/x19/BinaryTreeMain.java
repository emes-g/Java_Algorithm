package x19;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeMain {
    static int[] lc = {0, 2, 4, 6, 0, 0, 0, 0, 0};  // left child
    static int[] rc = {0, 3, 5, 7, 0, 8, 0, 0, 0};  // right child

    public static void main(String[] args) {
        // 레벨 순회
        levelTraversal(1);

        // 전위 순회
        System.out.print("전위 순회 : ");
        preorderTraversal(1);
        System.out.println();

        // 중위 순회
        System.out.print("중위 순회 : ");
        inorderTraversal(1);
        System.out.println();

        // 후위 순회
        System.out.print("후위 순회 : ");
        postorderTraversal(1);
        System.out.println();
    }

    // 레벨 순회
    public static void levelTraversal(int start) {
        Queue<Integer> q = new LinkedList<>();
        System.out.printf("레벨 순회 : %d ", start);
        q.offer(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (lc[curr] != 0) {
                System.out.printf("%d ", lc[curr]);
                q.offer(lc[curr]);
            }
            if (rc[curr] != 0) {
                System.out.printf("%d ", rc[curr]);
                q.offer(rc[curr]);
            }
        }
        System.out.println();
    }

    // 전위 순회
    // 현재 정점 방문 → 왼쪽 트리 순회 → 오른쪽 트리 순회
    public static void preorderTraversal(int curr) {
        System.out.printf("%d ", curr);
        if (lc[curr] != 0) {
            preorderTraversal(lc[curr]);
        }
        if (rc[curr] != 0) {
            preorderTraversal(rc[curr]);
        }
    }

    // 중위 순회
    // 왼쪽 트리 순회 → 현재 정점 방문 → 오른쪽 트리 순회
    public static void inorderTraversal(int curr) {
        if (lc[curr] != 0) {
            inorderTraversal(lc[curr]);
        }
        System.out.printf("%d ", curr);
        if (rc[curr] != 0) {
            inorderTraversal(rc[curr]);
        }
    }

    // 후위 순회
    // 왼쪽 트리 순회 → 오른쪽 트리 순회 → 현재 정점 방문
    public static void postorderTraversal(int curr) {
        if (lc[curr] != 0) {
            postorderTraversal(lc[curr]);
        }
        if (rc[curr] != 0) {
            postorderTraversal(rc[curr]);
        }
        System.out.printf("%d ", curr);
    }
}
