package x0c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p16987_sol1 {
    static int n, ans, cnt;
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
            ans = Math.max(ans, cnt);
            return;
        }
        if (eggs[index].isBroken() || cnt == n - 1) {
            func(index + 1);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (i == index || eggs[i].isBroken()) {
                continue;
            }
            eggs[index].durability -= eggs[i].weight;
            eggs[i].durability -= eggs[index].weight;
            if (eggs[index].isBroken()) {
                cnt++;
            }
            if (eggs[i].isBroken()) {
                cnt++;
            }
            func(index + 1);
            if (eggs[index].isBroken()) {
                cnt--;
            }
            if (eggs[i].isBroken()) {
                cnt--;
            }
            eggs[index].durability += eggs[i].weight;
            eggs[i].durability += eggs[index].weight;
        }
    }
}

class egg16987 {
    int durability, weight;

    public egg16987(int durability, int weight) {
        this.durability = durability;
        this.weight = weight;
    }

    public boolean isBroken() {
        return durability <= 0;
    }
}