package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

// TreeMap + TreeSet, almost O(nlogn)
// 별도의 자료구조 생성 없이 해결한 버전

public class p21939_sol1 {
    static int[] levels = new int[100001];  // (문제번호, 난이도)
    static TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();    // (난이도, 문제목록)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {   // O(nlogn)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            levels[num] = level;
            if (!map.containsKey(level)) {
                map.put(level, new TreeSet<>());
            }
            map.get(level).add(num);
        }
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {    // O(logn)
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                levels[num] = level;
                if (!map.containsKey(level)) {
                    map.put(level, new TreeSet<>());
                }
                map.get(level).add(num);
            } else if (cmd.equals("solved")) {  // O(logn)
                int num = Integer.parseInt(st.nextToken());
                int level = levels[num];
                levels[num] = 0;
                TreeSet<Integer> problems = map.get(level);
                if (problems.size() == 1) {
                    map.remove(level);
                    continue;
                }
                problems.remove(num);
            } else {    // O(logn * log(동일 난이도 문제 수))
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {   // 킬러 문제 번호 출력
                    int key = map.lastKey();
                    sb.append(map.get(key).last()).append('\n');
                } else {
                    int key = map.firstKey();
                    sb.append(map.get(key).first()).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}
