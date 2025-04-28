package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

// TreeSet + 배열
// TreeMap<Integer, Integer> → TreeSet<Integer> + int[]
// 기존 TreeMap의 (현재 팀들의 팀원 수, 해당 팀원 수를 가진 팀의 개수)를 분리

public class p19700_sol2 {
    static int n;
    static Student19700[] arr;
    static TreeSet<Integer> set = new TreeSet<>(); // 현재 팀들의 팀원 수
    static int[] cnt = new int[500001]; // 해당 팀원 수를 가진 팀의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new Student19700[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int fine = Integer.parseInt(st.nextToken());
            arr[i] = new Student19700(height, fine);
        }
        Arrays.sort(arr);   // O(nlogn)
        for (int i = 0; i < n; i++) {   // 느린 O(nlogn)
            Integer key = set.lower(arr[i].fine);
            if (key == null) {   // fine명 미만인 팀이 없는 경우 (팀을 신설해야 하는 경우)
                set.add(1);
                cnt[1]++;
                continue;
            }
            // 기존 팀에 들어가는 경우
            if (cnt[key] == 1) {    // key명 존재하는 팀이 1개인 경우
                set.remove(key);
            }
            cnt[key]--;
            if (cnt[key + 1] == 0) {    // (key + 1)명 존재하는 팀이 없는 경우
                set.add(key + 1);
            }
            cnt[key + 1]++; // always key < arr[i].fine이므로 상관 X
        }
        long ans = 0;
        for (Integer key : set) {
            ans += cnt[key];
        }
        System.out.println(ans);
    }
}