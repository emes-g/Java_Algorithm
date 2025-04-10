package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2457 {
    static int n, ans;
    static flower2457[] flowers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        flowers = new flower2457[n + 1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            flowers[i] = new flower2457(sm * 100 + sd, em * 100 + ed);
        }
        flowers[n] = new flower2457(1300, 1300);
        Arrays.sort(flowers);

        int start = 301;    // 탐색을 시작해야 하는 날짜 (현재 start에는 꽃이 안 피어있음)
        final int end = 1201;   // 탐색을 종료해도 되는 날짜 (start >= end면 탐색 종료)
        int now = 0;    // 현재 선택한 꽃들 중, 가장 늦게 지는 꽃의 지는 일자 (now에는 꽃이 안 피어있음)
        int index = 0;  // 선택할 꽃들을 보기 위해 필요한 인덱스
        while (start < end) {
            boolean flag = false;
            for (int i = index; i <= n; i++) {
                if (start < flowers[i].start) {
                    break;
                }
                if (now < flowers[i].end) { // 유의미한 꽃으로 갱신한 경우
                    flag = true;
                    now = flowers[i].end;
                }
                index++;    // 모든 꽃은 기본적으로 한 번씩만 본다 (최대 두 번)
            }
            if (flag) {
                ans++;
                start = now;
            } else {
                break;
            }
        }
        System.out.println(start >= end ? ans : 0);
    }
}

class flower2457 implements Comparable<flower2457> {
    int start, end;

    public flower2457(int start, int end) {
        this.start = start;
        this.end = end;
    }

    // 시작일 기준 오름차순, 시작일이 동일하다면 종료일 내림차순
    @Override
    public int compareTo(flower2457 o) {
        if (this.start == o.start) {
            return o.end - this.end;
        }
        return this.start - o.start;
    }

    @Override
    public String toString() {
        return "start:" + start + "\tend:" + end;
    }
}