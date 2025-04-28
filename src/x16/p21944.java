package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

// TreeSet + TreeMap

public class p21944 {
    static TreeSet<Problem21944> problems = new TreeSet<>();
    static TreeMap<Integer, TreeSet<Problem21944>> problemsByType = new TreeMap<>();
    static Problem21944[] problemsByNum = new Problem21944[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {   // O(nlogn)
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            Problem21944 problem = new Problem21944(num, level, type);
            problems.add(problem);
            if (!problemsByType.containsKey(type)) {
                problemsByType.put(type, new TreeSet<>());
            }
            problemsByType.get(type).add(problem);
            problemsByNum[num] = problem;
        }
        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("recommend")) {
                int type = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {   // 알고리즘 분류가 G인 문제 중, 킬러 문제 번호 출력
                    sb.append(problemsByType.get(type).last().num).append('\n');
                } else {
                    sb.append(problemsByType.get(type).first().num).append('\n');
                }
            } else if (cmd.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {   // 킬러 문제 번호 출력
                    sb.append(problems.last().num).append('\n');
                } else {
                    sb.append(problems.first().num).append('\n');
                }
            } else if (cmd.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                Problem21944 problem = new Problem21944(0, level, 0);
                if (x == 1) {   // 난이도가 L 이상인 문제 중, 가장 쉬운 문제 번호 출력
                    if (problems.ceiling(problem) == null) {
                        sb.append(-1).append('\n');
                        continue;
                    }
                    sb.append(problems.ceiling(problem).num).append('\n');
                } else {    // 난이도가 L 미만인 문제 중, 가장 어려운 문제 번호 출력
                    if (problems.lower(problem) == null) {
                        sb.append(-1).append('\n');
                        continue;
                    }
                    sb.append(problems.lower(problem).num).append('\n');
                }
            } else if (cmd.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                int type = Integer.parseInt(st.nextToken());
                Problem21944 problem = new Problem21944(num, level, type);
                problems.add(problem);
                if (!problemsByType.containsKey(type)) {
                    problemsByType.put(type, new TreeSet<>());
                }
                problemsByType.get(type).add(problem);
                problemsByNum[num] = problem;
            } else {
                int num = Integer.parseInt(st.nextToken());
                Problem21944 problem = problemsByNum[num];
                problems.remove(problem);
                if (problemsByType.get(problem.type).size() == 1) {
                    problemsByType.remove(problem.type);
                } else {
                    problemsByType.get(problem.type).remove(problem);
                }
                problemsByNum[num] = null;
            }
        }
        System.out.println(sb);
    }
}

class Problem21944 implements Comparable<Problem21944> {
    int num, level, type;

    public Problem21944(int num, int level, int type) {
        this.num = num;
        this.level = level;
        this.type = type;
    }

    // 난이도 기준 오름차순, 난이도가 동일하다면 문제번호 기준 오름차순
    @Override
    public int compareTo(Problem21944 o) {
        if (this.level == o.level) {
            return this.num - o.num;
        }
        return this.level - o.level;
    }
}