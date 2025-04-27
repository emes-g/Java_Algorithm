package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// TreeMap, O(t * k * logn)

public class p7662 {
    static int k;
    static TreeMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            k = Integer.parseInt(br.readLine());
            map = new TreeMap<>();
            while (k-- > 0) {   // O(k * logn), n == map.size()
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                Integer val = Integer.valueOf(st.nextToken());
                if (cmd.equals("I")) {
                    if (map.containsKey(val)) {
                        map.put(val, map.get(val) + 1);
                        continue;
                    }
                    map.put(val, 1);
                } else {
                    if (map.isEmpty()) {
                        continue;
                    }
                    Integer key = (val == 1) ? map.lastKey() : map.firstKey();
                    if (map.get(key) == 1) {
                        map.remove(key);
                        continue;
                    }
                    map.put(key, map.get(key) - 1);
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
                continue;
            }
            sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');
        }
        System.out.println(sb);
    }
}
