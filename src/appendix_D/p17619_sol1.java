package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 그리디, O(nlogn)

public class p17619_sol1 {
    static int n, q;    // n <= 십만, q <= 십만
    static tuple17619[] arr;
    static int[] group;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        arr = new tuple17619[n + 1];
        group = new int[n + 1];
        Arrays.fill(group, -1);
        arr[0] = new tuple17619(-1, -1, -1);
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            arr[i] = new tuple17619(x1, x2, i);
        }
        Arrays.sort(arr);   // O(nlogn)
        int end = arr[0].x2;
        int groupNum = 0;
        for (int i = 1; i <= n; i++) {
            if (end >= arr[i].x1) {  // 기존 그룹에 편입할 수 있는 경우
                end = Math.max(end, arr[i].x2);
                group[arr[i].orgNum] = groupNum;
                continue;
            }
            end = arr[i].x2;
            group[arr[i].orgNum] = ++groupNum;
        }
        StringBuilder sb = new StringBuilder();
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(group[u] == group[v] ? 1 : 0).append('\n');
        }
        System.out.println(sb);
    }
}

class tuple17619 implements Comparable<tuple17619> {
    int x1, x2;
    int orgNum; // original_number (기존 통나무 번호)

    public tuple17619(int x1, int x2, int orgNum) {
        this.x1 = x1;
        this.x2 = x2;
        this.orgNum = orgNum;
    }

    @Override
    public int compareTo(tuple17619 o) {
        if (this.x1 == o.x1) {
            return o.x2 - this.x2;
        }
        return this.x1 - o.x1;
    }
}
