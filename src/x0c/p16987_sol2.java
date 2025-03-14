package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p16987_sol2 {
    static int n, ans;
    static egg16987[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        eggs = new egg16987[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new egg16987(durability, weight);
        }
        func(0);
        System.out.println(ans);
    }

    public static void func(int index) {
        if (index == n) {
            int cnt = 0;
            for (egg16987 egg : eggs) {
                if (egg.isBroken()) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        if (eggs[index].isBroken()) {   // 내가 든 계란이 깨진 경우
            func(index + 1);
            return;
        }
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (i == index || eggs[i].isBroken()) {
                continue;
            }
            flag = true;
            eggs[index].durability -= eggs[i].weight;
            eggs[i].durability -= eggs[index].weight;
            func(index + 1);
            eggs[index].durability += eggs[i].weight;
            eggs[i].durability += eggs[index].weight;
        }
        if (!flag) {    // 나를 제외한 모든 계란이 깨져 있던 경우
            func(index + 1);
        }
    }
}