package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

// TreeMap

public class p19700_sol1 {
    static int n;
    static Student19700[] arr;
    static TreeMap<Integer, Integer> map = new TreeMap<>(); // (나보다 큰 팀원 수, 팀 개수)

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
            if (map.lowerKey(arr[i].fine) == null) {    // 팀 신설해야 하는 경우
                if (!map.containsKey(1)) {  // 1명으로 구성된 팀이 없는 경우
                    map.put(1, 1);
                    continue;
                }
                map.put(1, map.get(1) + 1);
                continue;
            }
            // 기존 팀에 들어가는 경우
            int key = map.lowerKey(arr[i].fine);
            if (map.get(key) == 1) {    // key명 존재하는 팀이 1개인 경우
                map.remove(key);
            } else {
                map.put(key, map.get(key) - 1);
            }
            if (!map.containsKey(key + 1)) {
                map.put(key + 1, 1);
            } else {
                map.put(key + 1, map.get(key + 1) + 1);
            }
        }
        long ans = 0;
        for (Integer key : map.keySet()) {
            ans += map.get(key);
        }
        System.out.println(ans);
    }
}

class Student19700 implements Comparable<Student19700> {
    int height;
    int fine;  // 팀 내에서 fine등까지는 괜찮음

    public Student19700(int height, int fine) {
        this.height = height;
        this.fine = fine;
    }

    // 키 기준 내림차순
    @Override
    public int compareTo(Student19700 o) {
        return o.height - this.height;
    }
}