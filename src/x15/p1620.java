package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class p1620 {
    static int n, m;
    static HashMap<Integer, String> numberKey = new HashMap<>();
    static HashMap<String, Integer> nameKey = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            numberKey.put(i, name);
            nameKey.put(name, i);
        }
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {   // O(m)
            String str = br.readLine();
            if (str.charAt(0) >= '0' && str.charAt(0) <= '9') { // 입력이 숫자인 경우
                sb.append(numberKey.get(Integer.parseInt(str))).append('\n');
            } else {
                sb.append(nameKey.get(str)).append('\n');
            }
        }
        System.out.println(sb);
    }
}
