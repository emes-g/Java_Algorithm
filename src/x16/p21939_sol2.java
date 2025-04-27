package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

// TreeSet, O(nlogn)
// 자료구조 생성하여 해결한 버전

public class p21939_sol2 {
        static TreeSet<Problem21939> problems = new TreeSet<>(new Comparator<Problem21939>() {
        // 난이도 기준 오름차순, 난이도가 동일하다면 문제번호 기준 오름차순
        @Override
        public int compare(Problem21939 o1, Problem21939 o2) {
            if (o1.level == o2.level) {
                return o1.num - o2.num;
            }
            return o1.level - o2.level;
        }
    });
//    static TreeSet<Problem21939> problems = new TreeSet<>((o1, o2) -> {
//        if (o1.level == o2.level) {
//            return o1.num - o2.num;
//        }
//        return o1.level - o2.level;
//    });
    static int[] levels = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            problems.add(new Problem21939(num, level));
            levels[num] = level;
        }
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                problems.add(new Problem21939(num, level));
                levels[num] = level;
            } else if (cmd.equals("solved")) {
                int num = Integer.parseInt(st.nextToken());
                // 번호를 가지고 난이도를 알 수 있어야,
                // 객체의 동등성을 판단해서 집합에서 제거할 수 있음
                int level = levels[num];
                problems.remove(new Problem21939(num, level));
                levels[num] = 0;
            } else {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {   // 킬러 문제 번호 출력
                    sb.append(problems.last().num).append('\n');
                } else {
                    sb.append(problems.first().num).append('\n');
                }
            }
        }
        System.out.println(sb);
    }
}

class Problem21939 {
    int num, level;

    public Problem21939(int num, int level) {
        this.num = num;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Problem21939)) {
            return false;
        }
        Problem21939 p = (Problem21939) o;
        return Objects.equals(this.num, p.num) && Objects.equals(this.level, p.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.num, this.level);
    }
}