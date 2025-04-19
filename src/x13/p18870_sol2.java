package x13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 방법 1과 다르게 상하한 없이, 해쉬맵만 사용

public class p18870_sol2 {
    static int n;
    static int[] org, sorted;
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        org = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            org[i] = Integer.parseInt(st.nextToken());
        }
        sorted = Arrays.copyOf(org, n);
        Arrays.sort(sorted);    // O(nlogn)
        int num = 0;
        for (int i = 0; i < n; i++) {   // O(n)
            if (!map.containsKey(sorted[i])) {
                map.put(sorted[i], num++);
            }
        }
        for (int i = 0; i < n; i++) {   // O(n)
            sb.append(map.get(org[i])).append(' ');
        }
        System.out.println(sb);
    }
}
