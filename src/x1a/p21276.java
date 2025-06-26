package x1a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상 정렬 응용, O(n + m + nlogn)

public class p21276 {
    static int n, m;
    static String[] arr;    // 이름 목록
    static HashMap<String, ArrayList<String>> original = new HashMap<>();    // 조상-자손
    static HashMap<String, ArrayList<String>> map = new HashMap<>();    // 부모-자식
    static HashMap<String, Integer> indegree = new HashMap<>();
    static ArrayList<String> roots = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String name = st.nextToken();
            arr[i] = name;
            original.put(name, new ArrayList<>());
            map.put(name, new ArrayList<>());
            indegree.put(name, 0);
        }
        Arrays.sort(arr);   // O(nlogn)
        // 1. indegree 배열 채우기
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String child = st.nextToken();  // 자손
            String parent = st.nextToken();  // 조상
            original.get(parent).add(child);
            indegree.put(child, indegree.get(child) + 1);
        }
        // 2. 조상/자손 -> 부모/자식 개념으로 전환
        Queue<String> q = new LinkedList<>();
        for (String name : arr) {
            if (indegree.get(name) == 0) {
                q.offer(name);
                roots.add(name);
            }
        }
        while (!q.isEmpty()) {  // O(n + m)
            String parent = q.poll();
            for (String child : original.get(parent)) {
                indegree.put(child, indegree.get(child) - 1);
                if (indegree.get(child) == 0) {
                    q.offer(child);
                    map.get(parent).add(child);
                }
            }
        }
        // 3. 결과 출력
        // (1). 루트노드 출력
        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append('\n');
        for (String root : roots) {
            sb.append(root).append(' ');
        }
        sb.append('\n');
        // (2). 부모자식 관계 출력
        for (String parent : arr) { // O(nlogn), 자식의 수는 n 이하일 수밖에 없음
            ArrayList<String> children = map.get(parent);
            sb.append(parent).append(' ').append(children.size()).append(' ');
            Collections.sort(children);
            for (String child : children) {
                sb.append(child).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
