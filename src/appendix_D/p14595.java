package appendix_D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 그리디, O(mlogm)
// p17619와 달리, 정렬 전 순서가 중요하지 않은 문제

public class p14595 {
    static int n, m;    // n <= 백만, m <= 5000
    static pair14595[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new pair14595[m + 1];
        arr[0] = new pair14595(0, 0);
        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new pair14595(x, y);
        }
        Arrays.sort(arr);   // O(mlogm)
        int cnt = n;
        int start = arr[0].x;
        int end = arr[0].y;
        for (int i = 1; i <= m; i++) {
            if (end >= arr[i].x) {  // 합칠 방의 범위를 갱신
                end = Math.max(end, arr[i].y);
            } else {
                cnt -= (end - start);
                start = arr[i].x;
                end = arr[i].y;
            }
        }
        cnt -= (end - start);
        System.out.println(cnt);
    }
}

class pair14595 implements Comparable<pair14595> {
    int x, y;

    public pair14595(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(pair14595 p) {
        if (this.x == p.x) {
            return p.y - this.y;
        }
        return this.x - p.x;
    }
}