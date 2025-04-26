package x15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 해쉬맵, O(Math.max(n, m))

public class p17219 {
    static HashMap<String, String> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String address = st.nextToken();
            String pw = st.nextToken();
            map.put(address, pw);
        }
        StringBuilder sb = new StringBuilder();
        while (m-- > 0) {
            String address = br.readLine();
            sb.append(map.get(address)).append('\n');
        }
        System.out.println(sb);
    }
}