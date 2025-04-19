package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 해쉬맵 사용 X
// 좌표압축 = 중복제거 + 이분탐색
// 좌표압축은 언제? 입력값의 범위가 1~10억과 같이 굉장히 클 때, 입력값을 배열의 인덱스처럼 사용하고 싶은 경우
// ex. 2 4 -10 4 -9 → 2 3 0 3 1

public class p18870_sol3 {
    static int n;
    static int[] org, sorted;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        org = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            org[i] = Integer.parseInt(st.nextToken());
        }
        sorted = Arrays.copyOf(org, n);
        Arrays.sort(sorted);    // O(nlogn)
        list.add(sorted[0]);
        for (int i = 1; i < n; i++) {   // O(n)
            if (sorted[i] != sorted[i - 1]) {   // 배열에서의 중복제거 알고리즘
                list.add(sorted[i]);
            }
        }
        for (int i = 0; i < n; i++) {   // O(nlogn)
            sb.append(binarySearch(org[i])).append(' ');
        }
        System.out.println(sb);
    }

    public static int binarySearch(int target) {
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
