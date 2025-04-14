package x11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

// 시간복잡도 : O(k * max(k, n, nk)) = O(nk^2)

public class p1700 {
    static int n, k, ans;
    // 멀티탭에 꽂혀 있는 플러그들 중,
    // 재사용하지 않는 플러그나
    // 가장 늦게 재사용할 플러그를 뽑아야 함
    // (가장 마지막에 사용하는 플러그를 뽑는 것이 아님!!)
    static ArrayList<Integer> orders = new ArrayList<>();
    static HashSet<Integer> multiTap = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            orders.add(Integer.parseInt(st.nextToken()));
        }
        while (!orders.isEmpty()) {
            int curr = orders.remove(0);
            if (multiTap.contains(curr)) {  // 멀티탭에서 이미 사용중인 플러그인 경우
                continue;
            }
            if (multiTap.size() < n) {  // 멀티탭의 플러그가 꽉 찬 상황이 아닌 경우
                multiTap.add(curr);
                continue;
            }
            // 이후 재사용하지 않거나, 가장 늦게 재사용하는 플러그를 뽑아야함
            int maxIdx = -1;
            boolean flag = false;
            for (int plug : multiTap) {
                int idx = orders.indexOf(plug);
                if (idx == -1) {   // 재사용하지 않을 플러그가 꽂혀 있는 경우
                    multiTap.remove(plug);
                    ans++;
                    flag = true;
                    multiTap.add(curr);
                    break;
                }
                if (maxIdx < idx) {
                    maxIdx = idx;
                }
            }
            if (!flag) {
                multiTap.remove(orders.get(maxIdx));
                ans++;
                multiTap.add(curr);
            }
        }
        System.out.println(ans);
    }
}
