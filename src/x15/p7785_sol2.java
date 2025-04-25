package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

// 해시, O(nlogn)

public class p7785_sol2 {
    static HashSet<String> set = new HashSet<>();
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        while (n-- > 0) {   // O(n)
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String act = st.nextToken();
            if (act.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }
        arr = new String[set.size()];
        int idx = 0;
        for (String name : set) {
            arr[idx++] = name;
        }
        Arrays.sort(arr, Collections.reverseOrder());   // O(nlogn)
        for (int i = 0; i < idx; i++) {
            sb.append(arr[i]).append('\n');
        }
        System.out.println(sb);
    }
}
