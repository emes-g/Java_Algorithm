package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class p18869 {
    static int m, n, ans;
    static int[][] space, sortedSpace;
    static List<List<Integer>> uniqueSortedSpace = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        space = new int[m][n];
        sortedSpace = new int[m][n];
        for (int i = 0; i < m; i++) {  // O(mn)
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 1. 정렬
        for (int i = 0; i < m; i++) {
            sortedSpace[i] = Arrays.copyOf(space[i], n);
            Arrays.sort(sortedSpace[i]);
        }
        // 2. 중복 제거
        for (int i = 0; i < m; i++) {
            uniqueSortedSpace.add(new ArrayList<>());
            List<Integer> curr = uniqueSortedSpace.get(i);
            curr.add(sortedSpace[i][0]);
            for (int j = 1; j < n; j++) {
                if (sortedSpace[i][j] != sortedSpace[i][j - 1]) {
                    curr.add(sortedSpace[i][j]);
                }
            }
        }
        // 3. 이분 탐색
        for (int i = 0; i < m; i++) {   // O(mnlogn)
            for (int j = 0; j < n; j++) {
                space[i][j] = binarySearch(uniqueSortedSpace.get(i), space[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {   // O(m^2 * n)
            for (int j = i + 1; j < m; j++) {
                if (Arrays.equals(space[i], space[j])) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static int binarySearch(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
