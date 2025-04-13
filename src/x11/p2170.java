package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2170 {
    static final int MAX = 1000000001;
    static int n, ans;
    static line2170[] lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        lines = new line2170[n + 1];
        lines[0] = new line2170(MAX, MAX);
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new line2170(s, e);
        }
        Arrays.sort(lines);
        int start = lines[0].start;
        int now = lines[0].end;
        for (int i = 1; i <= n; i++) {
            if (now < lines[i].start) { // 현재 선을 넘어가는 경우
                ans += (now - start);
                start = lines[i].start;
                now = lines[i].end;
                continue;
            }
            if (now < lines[i].end) {   // 현재 선이 연장되는 경우
                now = lines[i].end;
            }
        }
        System.out.println(ans);
    }
}

class line2170 implements Comparable<line2170> {
    int start, end;

    public line2170(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(line2170 o) {
        if (this.start == o.start) {
            return o.end - this.end;
        }
        return this.start - o.start;
    }
}
