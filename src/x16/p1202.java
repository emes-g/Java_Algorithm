package x16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 그리디(가격 내림차순 정렬) + TreeMap, O(klogk + nlogk)

public class p1202 {
    static int n, k;
    static Item1202[] arr;
    static TreeMap<Integer, Integer> bags = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new Item1202[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            arr[i] = new Item1202(weight, price);
        }
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {   // O(klogk)
            int capacity = Integer.parseInt(br.readLine());
            if (!bags.containsKey(capacity)) {
                bags.put(capacity, 1);
            } else {
                bags.put(capacity, bags.get(capacity) + 1);
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {   // O(nlogk)
            if (bags.ceilingKey(arr[i].weight) == null) {   // 현재 보석을 담을 수 있는 가방이 없는 경우
                continue;
            }
            int key = bags.ceilingKey(arr[i].weight);   // 이상인 값 중 최소
            int val = bags.get(key);
            ans += arr[i].price;
            if (val == 1) {
                bags.remove(key);
                continue;
            }
            bags.put(key, bags.get(key) - 1);
        }
        System.out.println(ans);
    }
}

class Item1202 implements Comparable<Item1202> {
    int weight, price;

    public Item1202(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    // 가격 내림차순, 가격이 동일하다면 무게 오름차순
    @Override
    public int compareTo(Item1202 o) {
        if (this.price == o.price) {
            return this.weight - o.weight;
        }
        return o.price - this.price;
    }
}
