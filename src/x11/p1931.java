package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1931 {
    static int n;
    // 귀류법으로, 종료 시간이 가장 빠르지 않은 요소를 선택한다고 하자.
    // 그러면, 종료 시간이 가장 빠른 요소를 선택했을 때보다 최대로 사용할 수 있는 회의실 개수가 적거나 같을 수밖에 없다.
    // 따라서, 종료 시간이 가장 빠른 요소를 택해야 한다.
    static Meeting[] meetings;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        meetings = new Meeting[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            meetings[i] = new Meeting(startTime, endTime);
        }
        Arrays.sort(meetings);
        int lastMeetingEndTime = -1;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (lastMeetingEndTime <= meetings[i].startTime) {
                lastMeetingEndTime = meetings[i].endTime;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

class Meeting implements Comparable<Meeting> {
    int startTime, endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // 시작 시간과 종료 시간이 동일한 경우를 고려
    @Override
    public int compareTo(Meeting o) {
        if (this.endTime != o.endTime) {
            return this.endTime - o.endTime;
        }
        return this.startTime - o.startTime;
    }
}