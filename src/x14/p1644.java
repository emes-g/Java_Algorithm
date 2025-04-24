package x14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 투 포인터 (open interval), O(n)

public class p1644 {
    static int n, ans;
    static boolean[] isPrime;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {  // almost O(n)
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
        for (int i = 2; i <= n; i++) {  // O(n)
            if (isPrime[i]) {
                list.add(i);
            }
        }
        int size = list.size();
        int end = 0;
        int sum = 0;    // [start, end) 합
        for (int start = 0; start < size; start++) {    // O(n)
            while (end < size && sum + list.get(end) < n) { // 끝까지 가든가, [start, end] 합이 n 이상이 될때까지 진행
                sum += list.get(end);
                end++;
            }
            if (end == size) {
                break;
            }
            if (sum + list.get(end) == n) {
                ans++;
            }
            sum -= list.get(start);
        }
        System.out.println(ans);
    }
}
