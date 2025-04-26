package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 조합, 시간 초과 O(t * 2^n)
// 업로드 이유: 조합 숙련도

public class p9375_fail1 {
    static int n, ans;
    static HashMap<String, Boolean> check;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            ans = 0;
            check = new HashMap<>();
            arr = new String[n];
            for (int i = 0; i < n; i++) {   // O(n)
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                check.put(type, false);
                arr[i] = type;
            }
            func(0);
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }

    // 최악은 O(2^n)인데, 가지치기하므로 여기까지 갈 일은 거의 없긴 함
    // 그래도 시간초과는 맞음 (로직은 맞음)
    public static void func(int index) {
        if (index >= n) {
            return;
        }
        String type = arr[index];
        if (check.get(type)) {
            func(index + 1);    // 이미 해당 종류가 채워져 있는 경우
            return;
        }
        ans++;
        check.put(type, true);
        func(index + 1);    // 이번 옷을 입어서, 해당 종류를 채우는 경우
        check.put(type, false);
        func(index + 1);    // 이번 옷을 입지 않고, 해당 종류를 채우지 않는 경우
    }
}
