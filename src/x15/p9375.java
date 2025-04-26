package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 해쉬맵, O(t * n)
// "안 입는다"는 선택지를 추가하는 것이 포인트

public class p9375 {
    static int n, ans;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for (int i = 0; i < n; i++) {   // O(n)
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                if (!map.containsKey(type)) {
                    map.put(type, 1);   // "안 입는다"는 선택지를 추가
                }
                map.put(type, map.get(type) + 1);
            }
            ans = 1;
            for (String type : map.keySet()) {
                ans *= map.get(type);
            }
            sb.append(ans - 1).append('\n');    // 아무것도 안 입는 경우는 제외
        }
        System.out.println(sb);
    }
}
