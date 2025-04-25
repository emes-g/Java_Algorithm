package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 문자열 이분탐색 (해시 없이 풀어본 버전)
// 시간복잡도: O(nlogn + n * cnt)

public class p7785_sol1 {
    static int size;
    static List<String> enters = new ArrayList<>();
    static Queue<String> leaves = new LinkedList<>();
    static Person7785[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {   // O(n)
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String act = st.nextToken();
            if (act.equals("enter")) {
                enters.add(name);
            } else {
                leaves.add(name);
            }
        }
        size = enters.size();
        arr = new Person7785[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new Person7785(enters.get(i), true);
        }
        Arrays.sort(arr);   // O(nlogn)
        while (!leaves.isEmpty()) {
            String name = leaves.poll();
            int lower = lowerBound(name);
            int upper = upperBound(name);
            for (int i = lower; i < upper; i++) {   // O(cnt)
                if (arr[i].status) {
                    arr[i].status = false;
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i].status) {
                sb.append(arr[i].name).append('\n');
            }
        }
        System.out.println(sb);
    }

    // 문자열 이분탐색(하한)
    public static int lowerBound(String target) {
        int start = 0;
        int end = size;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid].name.compareTo(target) >= 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    public static int upperBound(String target) {
        int start = 0;
        int end = size;
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid].name.compareTo(target) > 0) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

class Person7785 implements Comparable<Person7785> {
    String name;
    boolean status;

    public Person7785(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    // 이름 기준 오름차순
    @Override
    public int compareTo(Person7785 o) {
        return name.compareTo(o.name);
    }
}
